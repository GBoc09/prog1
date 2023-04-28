package com.example.prog1.controller.applicativo;

import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.dao.*;
import com.example.prog1.bean.*;
import com.example.prog1.model.*;

import java.util.ArrayList;
import java.util.List;

public class RentalEquipApplicativo {
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
    public List<DivingBean> getDivingListMan(UserBean userBean)  {
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
    public void deleteItem(UserBean userBean)  {
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.deleteItemsFromCart(userBean.getUserEmail());
    }
    public void deleteRent(UserBean userBean)  {
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.deleteItemsFromRental(userBean.getUserEmail());
    }
    /**
     * e communication bean non ritorna il valore corretto se riprendiamo il processo di comprare cose dopo aver
     * chiuso l'applicazione
     *
     * RIEPILOGO DEI DATI NELLA SCHERMATA (FXML) RENTAL FUNZIONANTE
     */
    public void saveItem(List<CartBean> cartBeans, UserBean userBean, CominicationBean cominicationBean) {
        RentalDAO rentalDAO = new RentalDAO();
        String type = null;
        Integer price = 0;
        Integer total = 0;
        for (CartBean d : cartBeans){
            type = d.getType();
            price = d.getPrice();
            total = total+price;

            rentalDAO.insertRent(type, userBean.getUserEmail(), cominicationBean.getStr(), price, total);
        }
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
    public void sendEmail(VendorOrderBean vendorOrderBean) {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        List<Manager> managers = getEmailManager();
        (vendorOrderBean).setOrderOwnerEmail(userBean.getUserEmail());
        for (Manager manger : managers){
            (vendorOrderBean).addSubscribedEmail(manger.getEmail());
        }
/**        super(sendEmail(vendorOrderBean));*/
        vendorOrderBean.notifyRental();
    }
    private List<Manager> getEmailManager()  {
        ManagerDAO managerDAO = new ManagerDAO();
        List<Manager> manger = new ArrayList<>();
        Manager man;
        man = managerDAO.selectEmailMan();
        manger.add(man);
        return manger;
    }
    public List<RentalBean> summaryRental (String user) {
        List<RentalBean> equip = new ArrayList<>();
        RentalDAO rentalDAO = new RentalDAO();
        List<Rental> equip2 = rentalDAO.getRentInfo(user);
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
}

