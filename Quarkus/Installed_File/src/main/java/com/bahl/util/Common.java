package com.bahl.util;

import java.io.FileWriter;
 
import java.util.List;
 
import com.fasterxml.jackson.databind.ObjectMapper;

public class Common {


    public static void writeInFile(List<Object> objects, String fileName){
        ObjectMapper mapper = new ObjectMapper();
            // Convert JSON string from file to Object
        try {
            String data = mapper.writeValueAsString(objects);
            FileWriter fileWriter = new FileWriter("src/main/resources/" + fileName + ".json");
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }

    
}
