package com.example.prog1.controller.applicativo;

import com.example.prog1.DAO.CartFileSaver;
import com.example.prog1.DAO.EquipDAO;
import com.example.prog1.DAO.RentalDAO;
import com.example.prog1.DAO.ScubaDAO;
import com.example.prog1.bean.CartBean;
import com.example.prog1.bean.CartRowBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.catalogue.EquipCatalogue;
import com.example.prog1.exception.NotExistentUserException;
import com.example.prog1.model.*;

import java.util.ArrayList;
import java.util.List;

public class RentalEquipApplicativo {
    private final EquipCatalogue equipCatalogue;
    private Cart cart;
    private Scuba scuba;
    private CartBean cartBean;
    private CartFileSaver cartFileSaver;

    public RentalEquipApplicativo(UserBean loggedUser){
        EquipDAO equipDAO = new EquipDAO();
        equipCatalogue = equipDAO.loadAllProducts();
        try{
            loadScuba(loggedUser);
            cart = cartFileSaver.loadCartFromFile();
        }catch (NotExistentUserException e){
            cart = new Cart();
        }
    }
    public void loadScuba(UserBean userBean) throws NotExistentUserException {
        if(userBean == null) throw new NotExistentUserException("You are not logged");
        ScubaDAO scubaDAO = new ScubaDAO();
        scuba = scubaDAO.loadScubaByEmail(userBean.getUserEmail());
        cartFileSaver = new CartFileSaver(scuba.getEmail());
        if(cart != null){
            cartFileSaver.saveCartInFile(cart);
        }
    }
    public List<EquipBean>  createArrayEquipBean (List<Equipment> equipmentList){
        List<EquipBean> equipBeanList = new ArrayList<>();
        for (Equipment equipment : equipmentList){
            EquipBean equipBean = new EquipBean(equipment.getEquipID(), equipment.getEquipType(), equipment.getSize(), equipment.getAvail(), equipment.getPrice());
            equipBeanList.add(equipBean);
        }
        return equipBeanList;
    }
    public CartBean insertItemToCart(EquipBean equipBean){
        Equipment equipment = equipCatalogue.getEquipByID(equipBean.getId());
        cart.insertEquip(equipment);
        if(scuba != null) {
            cartFileSaver.saveCartInFile(cart);
        }
        return createCartBean();
    }
    public CartBean showCart(){
        return createCartBean();
    }
    private CartBean createCartBean(){
        List<CartRow> cartRows = cart.getCartRowArrayList();
        CartBean cartBean = new CartBean();
        List<CartRowBean> cartRowBeans = new ArrayList<>();
        for (CartRow cartRow : cartRows){
            cartRowBeans.add(new CartRowBean(cartRow.getEquipType(),cartRow.getEquipId(),cartRow.getQuantity(),cartRow.getPrice(),cartRow.getEquipSize()));
        }
        cartBean.setCartRowBeanList(cartRowBeans);
        return cartBean;
    }
    public CartBean removeItemFromCart(EquipBean equipBean){
        Equipment rmvEquip = equipCatalogue.getEquipByID(equipBean.getId());
        cart.removeEquip(rmvEquip);
        if(scuba != null){
            cartFileSaver.saveCartInFile(cart);
        }
        return createCartBean();
    }
    public void completeRental(){
        Rental rental = new Rental();
        rental.setRentalOwner(scuba);
        rental.setTotal(cartBean.getTotal());
        rental.setCartRows(cart.getCartRowArrayList());

        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.saveRent(rental);

        cartFileSaver.deleteCartFromFile();



    }
}
