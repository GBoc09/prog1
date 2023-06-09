package com.example.prog1.controller.applicativo;

import com.example.prog1.boundary.RentEquipDecisionEmail;
import com.example.prog1.boundary.RentEquipEmail;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.dao.*;
import com.example.prog1.bean.*;
import com.example.prog1.model.*;

import java.util.ArrayList;
import java.util.List;

public class RentalEquipApplicativo {
    private static final String ACCEPT = "accept";
    private Integer id = 0;
    public RentalEquipApplicativo(){/* costruttore */ }
    /** seleziona tutte le attrezzature */
    public List<EquipBean> getEquips () {
        List<EquipBean> equip = new ArrayList<>();
        EquipDAO equipmentDAO = new EquipDAO();
        List<Equipment> equip2 = equipmentDAO.getEquipInfo();
        for (Equipment d : equip2) {
            EquipBean equipmentBean = new EquipBean();
            equipmentBean.setType(d.getEquipType());
            equipmentBean.setSize(d.getSize());
            equipmentBean.setAvail(d.getAvail());
            equipmentBean.setPrice(d.getPrice());
            equip.add(equipmentBean);
        }
        return equip;
    }
    /** diving name = name
     *
     * seleziona le equip con il diving name */

    public List<EquipBean> getEquipsName (String name) {
        List<EquipBean> equip = new ArrayList<>();
        EquipDAO equipmentDAO = new EquipDAO();
        List<Equipment> equip2 = equipmentDAO.getEquipInfoName(name);
        for (Equipment d : equip2) {
            EquipBean equipmentBean = new EquipBean();
            equipmentBean.setType(d.getEquipType());
            equipmentBean.setSize(d.getSize());
            equipmentBean.setAvail(d.getAvail());
            equipmentBean.setPrice(d.getPrice());
            equip.add(equipmentBean);
        }
        return equip;
    }
    public static List<EquipBean> getEquips(UserBean userBean, CominicationBean cominicationBean) {
        List<EquipBean> equip = new ArrayList<>();
        EquipDAO equipDAO = new EquipDAO();
        List<Equipment> equip2 = equipDAO.getEquipInfoManEDiv(userBean, cominicationBean);
        for (Equipment d : equip2) {
            EquipBean equipBean = new EquipBean();
            equipBean.setType(d.getEquipType());
            equipBean.setSize(d.getSize());
            equipBean.setAvail(d.getAvail());
            equipBean.setPrice(d.getPrice());
            equip.add(equipBean);
        }
        return equip;
    }
    /** lista di tutti i diving */
    public List<DivingBean> getDivingList() {
        List<DivingBean> div = new ArrayList<>();
        DivingDAO divingDAO = new DivingDAO();
        List<Diving> div2 = divingDAO.getDivInfo();
        for (Diving d : div2) {
            DivingBean divingBean = new DivingBean();
            divingBean.setName(d.getName());
            divingBean.setLocation(d.getLocation());
            divingBean.setTelephone(d.getTelephone());
            div.add(divingBean);
        }
        return div;
    }
    /** lista dei diving associatiad un manager */
    public static List<DivingBean> getDivingListMan(UserBean userBean)  {
        List<DivingBean> div = new ArrayList<>();
        DivingDAO divingDAO = new DivingDAO();
        List<Diving> div2 = divingDAO.getDivInfoMan(userBean);
        for (Diving d : div2) {
            DivingBean divingBean = new DivingBean();
            divingBean.setName(d.getName());
            divingBean.setLocation(d.getLocation());
            divingBean.setTelephone(d.getTelephone());
            div.add(divingBean);
        }
        return div;
    }
    /** mostra il carrello */
    public List<CartBean> showCart (String email) {
        List<CartBean> cart = new ArrayList<>();
        EquipDAO equipDAO = new EquipDAO();
        List<Equipment> equip2 = equipDAO.getCart(email);
        for (Equipment d : equip2) {
            CartBean cartBean1 = new CartBean();
            cartBean1.setType(d.getEquipType());
            cartBean1.setSize(d.getSize());
            cartBean1.setQuant(d.getAvail());
            cartBean1.setPrice(d.getPrice());
            cart.add(cartBean1);
        }
        return cart;
    }
    public List<CartBean> showCartMan () {
        List<CartBean> cart = new ArrayList<>();
        EquipDAO equipDAO = new EquipDAO();
        List<Equipment> equip2 = equipDAO.getCartMan();
        for (Equipment d : equip2) {
            CartBean cartBean1 = new CartBean();
            cartBean1.setType(d.getEquipType());
            cartBean1.setSize(d.getSize());
            cartBean1.setQuant(d.getAvail());
            cartBean1.setPrice(d.getPrice());
            cart.add(cartBean1);
        }
        return cart;
    }
    public void deleteItem(UserBean userBean)  {
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.deleteItemsFromCart(userBean.getUserEmail());
    }public void deleteItem()  {
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.deleteItemsFromCart();
    }
    public void deleteRent(UserBean userBean)  {
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.deleteItemsFromRental(userBean.getUserEmail());
    }
    public void saveItem(List<CartBean> cartBeans, UserBean userBean, CominicationBean cominicationBean) {
        /** query per info vendorOrder bean
         * new vendor order bean
         * new boundary
         * attach boundary */
        RentalDAO rentalDAO = new RentalDAO();
        String man = rentalDAO.divingEmail(cominicationBean.getStr());
        VendorOrderBean v = new VendorOrderBean(man, userBean.getUserEmail());
//        RentEquipEmail r = new RentEquipEmail(v);
        new RentEquipEmail(v);

        String type = "";
        Integer price = 0;
        Integer quant = 0;
        Integer total = 0;
        for (CartBean d : cartBeans){
            type = d.getType();
            quant = d.getQuant();
            price = d.getPrice();
            total = total+(price*quant);

            rentalDAO.insertRent(type, userBean.getUserEmail(), cominicationBean.getStr(), price, total);
        }
        /** notify */
        v.notifyChanges();
        id = rentalDAO.idRent();
        IntegerComunicationBean integerComunicationBean = new IntegerComunicationBean(id);
        InternalControllerGrafico.getInternalControllerInstance().setBeanInt(integerComunicationBean);
    }
    public EquipBean infoEquipGeneral(int selectedIndex){
        EquipDAO equipDAO = new EquipDAO();
        EquipBean equipBean = null;
        equipBean = equipDAO.selectEquipByOrder(selectedIndex);
        return equipBean;
    }
    public Integer infoDispEquip(int index) {
        EquipDAO equipDAO = new EquipDAO();
        Integer i;
        i = equipDAO.selectAvailability(index);
        return i;
    }
    public void infoEquipCart(int selectedIndex, int quant, String email) {
        EquipDAO equipDAO = new EquipDAO();
        CartDAO cartDAO = new CartDAO();
        EquipBean equipBean = null;
        equipBean = equipDAO.selectEquipByOrder(selectedIndex);
        cartDAO.insertIntoCart(equipBean, quant, email);
    }
    public void infoEquipCartCLI(PassStringsBean passStringsBean, UserBean userBean) {
        String item = passStringsBean.getItem();
        String size = passStringsBean.getSize();
        Integer quant = passStringsBean.getQuant();
        String user = userBean.getUserEmail();
        CartDAO cartDAO = new CartDAO();
        cartDAO.insertIntoCartCLI(item,size,quant,user);
    }
    public List<RentalBean> summaryRental (String user, Integer id) {
        List<RentalBean> equip = new ArrayList<>();
        RentalDAO rentalDAO = new RentalDAO();
        List<Rental> equip2 = rentalDAO.getRentInfo(user, id);
        for (Rental d : equip2) {
            RentalBean rentalBean = new RentalBean();
            rentalBean.setIdRental(d.getIdRent());
            rentalBean.setEquipType(d.getEquipType());
            rentalBean.setDiv(d.getDiving());
            rentalBean.setTotal(d.getTotal());
            equip.add(rentalBean);
        }
        return equip;
    }
    public void sendConfirmation(String divingMan, String decisione){
        RentEquipDecisionEmail rentEquipDecisionEmail = new RentEquipDecisionEmail();
        RentalDAO rentalDAO = new RentalDAO();
        String buyerInfo = rentalDAO.buyerInfo(divingMan);
        if (decisione.equals(ACCEPT)){
            rentEquipDecisionEmail.notifyBuyersOK(createNotificationInfoB(buyerInfo, divingMan));
        } else {
            rentEquipDecisionEmail.notifyBuyersKO(createNotificationInfoB(buyerInfo, divingMan));
        }
    }
    public BuyerOrderBean createNotificationInfoB(String orderOwner, String vendor){
        return new BuyerOrderBean(orderOwner, vendor);
    }

    public void addProduct (EquipBean equipBean, CominicationBean cominicationBean, UserBean userBean)  {
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.insertEquip(equipBean, cominicationBean.getStr(), userBean.getUserEmail());
    }
    public void addDiving(UserBean userBean, DivingBean divingBean)  {
        DivingDAO divingDAO = new DivingDAO();
        divingDAO.insertDiving(divingBean, userBean.getUserEmail());
    }

}

