package com.example.prog1;

import com.example.prog1.bean.VendorOrderBean;
import com.example.prog1.boundary.RentEquipEmail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

public class RentEquipEmailTest {
    @Test
    public void TestFileWritten(){
        RentEquipEmail rentEquipEmail = new RentEquipEmail();
        VendorOrderBean bean = new VendorOrderBean("a", "b");
        try {
            rentEquipEmail.sendEmail(bean);
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
            Assertions.assertEquals(1,0,0); // always false
        }
    }
}
