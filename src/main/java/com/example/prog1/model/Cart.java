package com.example.prog1.model;

import com.example.prog1.pattern.decorator.Price;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable, Price {
    private final ArrayList<CartRow> cartRowArrayList;

    public Cart(){
        cartRowArrayList = new ArrayList<>();
    }
    public void insertEquip(Equipment newEquip){
        CartRow equipRow = verifyPresence(newEquip);
        if (equipRow == null){
            cartRowArrayList.add(new CartRow(newEquip,1));
        } else {
            equipRow.setQuantity(equipRow.getQuantity()+1);
        }
    }
    public void removeEquip(Equipment rmvEquip){
        CartRow cartRow = verifyPresence(rmvEquip);
        if(cartRow != null){
            cartRow.setQuantity(cartRow.getQuantity()-1);
            if(cartRow.getQuantity() == 0){
                cartRowArrayList.remove(cartRow);
            }
        }
    }
    private CartRow verifyPresence(Equipment product){
        for (CartRow row: cartRowArrayList){
            if(row.getEquipment().equals(product)){
                return row;
            }
        }
        return null;
    }
    public ArrayList<String> getVendorInfo(){
        ArrayList<String> vendorInfo = new ArrayList<>();
        for (CartRow cartRow: cartRowArrayList){
            if (!vendorInfo.contains(cartRow.getEquipVendor())){
                vendorInfo.add(cartRow.getEquipVendor());
            }
        }
        return vendorInfo;
    }
    public List<CartRow> getCartRowArrayList(){
        return this.cartRowArrayList;
    }
    @Override
    public Double getPrice(){
        Double total = 0.0;
        for (CartRow cartRow :cartRowArrayList){
            total = total + cartRow.getSubTotal();
        }
        return total;
    }
}
