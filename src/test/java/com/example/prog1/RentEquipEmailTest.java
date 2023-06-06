package com.example.prog1;

import com.example.prog1.bean.VendorOrderBean;
import com.example.prog1.boundary.RentEquipEmail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

class RentEquipEmailTest {
    @Test
   void TestFileWritten(){
        VendorOrderBean bean = new VendorOrderBean("a", "b");
        RentEquipEmail rentEquipEmail = new RentEquipEmail(bean);
        try {
            rentEquipEmail.update();
            Scanner reader = new Scanner(new File("vendorNotifyFile"));
            String data = "";
            while(reader.hasNextLine()){
                data += reader.nextLine() + "\n";
            }

            String s = String.format("""
                    Dear %s, 
                    We are pleased to inform you that %s has placed an order with you. 
                    Visit the details section to learn more. 
                    
                    Best Wishes
                    DiversWorld's team
                    """, bean.getVendor(), bean.getOrderOwner());

            Assertions.assertEquals(s,data);

        }
        catch (Exception e){
            Assertions.assertFalse(0>1, "exception occurred"); // always false
        }
    }
}
