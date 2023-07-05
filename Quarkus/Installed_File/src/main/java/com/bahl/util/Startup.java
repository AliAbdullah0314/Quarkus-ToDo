package com.bahl.util;

import java.io.FileReader;
import java.util.ArrayList;

import java.util.List;

import com.bahl.dto.PersonDto;
import com.bahl.dto.TaskDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * import libraries                                     :
 * java.util.Date                                       : Used to get instance of date
 * import javax.inject.Inject                           : Dependency injection used to inject classes
 * com.fasterxml.jackson.core.JsonProcessingException   : Used to Throw Json processing error on conversion
 * com.fasterxml.jackson.databind.ObjectMapper          : Used to map Json to String
 * org.eclipse.microprofile.config.inject.ConfigProperty: Used to fetch property from application.properties file
 * io.quarkus.runtime.Quarkus                           :
 * io.quarkus.runtime.QuarkusApplication                :
 * io.quarkus.runtime.annotations.QuarkusMain           :
 * org.jboss.logging.Logger                             : Import to use logging for elastic search
 */

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * Startup is the class of esm-account service. This class is used to call
 * configuration on startup of application and cache it into constants variable
 * Service Names: Startup.java
 * 
 * @author : Syed Abbas Shamim Rizvi - Muhammad Bilal Un Nabi Khan, Farman Ali
 *         Ujjan, Suneeta Devi
 * @version 1.0
 * @return cache configuration retrieved into a hashmap variable
 * @brief Startup Class is used to call configuration on startup of application
 *        and cache it into constants variable
 */

@QuarkusMain
public class Startup {

    public static void main(String... args) {

        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            int exitcode = 0;

            List<PersonDto> personDtos = getPersonDtos("PEOPLES"); // loads persons on startup , returns a list of that
            //System.out.println(getPersonDtos("PEOPLES"));
            // System.out.println(personDtos.get(1));
            // System.out.println(personDtos.get(2));
            Constant.persons = personDtos;                                                                // specific type
           
             
            // Constant.persons = getDtos(fileName : "PEOPLES", Type PersonDto )
            //List<PersonDto> personDtos1 =  convertToListOfClass(getDtos("PEOPLES"), "PersonDto"); failed attempt
            
            List<TaskDto> taskDtos = getTaskDtos("TASKS");
            Constant.tasks = taskDtos;

            Quarkus.waitForExit();
            return exitcode;
        }



        public List<Object> getDtos(String fileName) { // return a TaskDto list that will populate my lis tasks
            List<Object> listOfDtos = new ArrayList<Object>();
            try (FileReader reader = new FileReader("src/main/resources/" + fileName + ".json")) {

                ObjectMapper mapper = new ObjectMapper();
                // Convert JSON string from file to Object

                listOfDtos = mapper.readValue(reader,
                        new TypeReference<List<Object>>() {
                        });

                return listOfDtos;
            } catch (Exception e) {

                return listOfDtos;
            }
        }     

        public List<TaskDto> getTaskDtos(String fileName) { // return a TaskDto list that will populate my list tasks
            List<TaskDto> listOfDtos = new ArrayList<TaskDto>();
            try (FileReader reader = new FileReader("src/main/resources/" + fileName + ".json")) {

                ObjectMapper mapper = new ObjectMapper();
                // Convert JSON string from file to Object

                listOfDtos = mapper.readValue(reader,
                        new TypeReference<List<TaskDto>>() {
                        });

                return listOfDtos;
            } catch (Exception e) {

                return listOfDtos;
            }
        }

        public List<PersonDto> getPersonDtos(String fileName) {
            List<PersonDto> listOfDtos = new ArrayList<PersonDto>();
            try (FileReader reader = new FileReader("Quarkus/Installed_File/src/main/resources/" + fileName + ".json")) {
//Quarkus/Installed_File/src/main/resources/PEOPLES.json
///workspaces/Quarkus-ToDo/Quarkus/Installed_File/src/main/resources/PEOPLES.json
                ObjectMapper mapper = new ObjectMapper();
                // Convert JSON string from file to Object

                listOfDtos = mapper.readValue(reader,
                        new TypeReference<List<PersonDto>>() {
                        });
                //System.out.println("Within function: "+listOfDtos);

                return listOfDtos;
            } catch (Exception e) {

                //System.out.println("Catch Within function: "+ e);


                return listOfDtos;
            }

        }

        // public List<Object> getPersonDtos(String fileName) {
        // List<Object> listOfDtos = new ArrayList<>();
        // try (FileReader reader = new FileReader("src/main/resources/" + fileName +
        // ".json")) {

        // ObjectMapper mapper = new ObjectMapper();
        // // Convert JSON string from file to Object

        // listOfDtos = mapper.readValue(reader,
        // new TypeReference<List<Object>>() {
        // });

        // return listOfDtos;
        // } catch (Exception e) {

        // return listOfDtos;
        // }

        // }

                    // public static <T> List<T> convertToListOfClass(List<?> objectList, String className) {
        //     List<T> convertedList = new ArrayList<>();

        //     try {
        //         Class<?> targetClass = Class.forName(className);

        //         for (Object obj : objectList) {
        //             T convertedObj = (T) targetClass.cast(obj);
        //             convertedList.add(convertedObj);
        //         }
        //     } catch (ClassNotFoundException e) {
        //         e.printStackTrace();
        //     }

        //     return convertedList;
        // }



    }
}