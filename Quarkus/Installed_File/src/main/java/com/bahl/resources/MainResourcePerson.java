package com.bahl.resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import com.bahl.dto.PersonDto;
import com.bahl.dto.ProductDto;
import com.bahl.dto.ProjectDto;
import com.bahl.dto.TaskDto;
import com.bahl.util.Common;
import com.bahl.util.Constant;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/to-do-list")
public class MainResourcePerson {

    static int personId = 1;

    

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome() {
        setPersonId();
        return "Welcome to the ToDoList";
        // return Response.ok(persons).build();
    }

    public void setPersonId()
    {
        int length = numPersons();
        personId = Constant.persons.get(length-1).id + 1;
    }





    // shows as a list of all the people and description of those who have been
    // added
    // can add status to class person if someone is available and then show them
    // only
    @GET
    @Path("/persons")
    public List<PersonDto> peopleAvailable() {
        return Constant.persons;
    }

    @GET
    @Path("/persons/{id}")
    public Response peopleAvailable(@PathParam("id") int id) {
        PersonDto personDto = new PersonDto();
        if (id >= 1 && id <= Constant.persons.size()) {
            personDto = Constant.persons.get(id - 1);
        }
        return Response.ok(personDto).build();
    }

    // gives the number of people that have been added
    @GET
    @Path("persons/size")
    public int numPersons() {
        System.out.println("Entered size method");
        return Constant.persons.size();
    }

    @POST
    @Path("/add-person")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(PersonDto newPerson) {
        //newPerson.id = Constant.persons.size() + 1; // the id is related to the size, first Constant.persons id will be
                                                    // 1 and so on
        newPerson.id = personId;
        personId++;
        newPerson.status = true;
        Constant.persons.add(newPerson);
        List<Object> objectListPersons = new ArrayList<>(Constant.persons);
        Common.writeInFile(objectListPersons, "PEOPLES");

        // return Response.ok(Constant.persons.get(0).id).build();
        return Response.ok(Constant.persons).build();
    }
    
    @DELETE
    @Path("/deletepersons/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        //System.out.println("Entered del method");
        if(findById(id, "persons")!=-1)
        {
            PersonDto personToDelete = Constant.persons.get(findById(id, "persons"));
            boolean removed = false;
            
            if (personToDelete.status)
            {
                removed = Constant.persons.remove(personToDelete);
            }

            if(removed)
            {
                return Response.ok().build();
            }
        }
        
        return Response.status(Response.Status.BAD_REQUEST).build();
       
    }

    public int findById(int id, String category)
    {
        if(category.equals("persons"))
        {
            int length = numPersons();
            for(int i = 0; i< length; i++)
            {
                if(Constant.persons.get(i).id == id)
                {
                    return i;
                }
            }
        }

        if(category.equals("tasks"))
        {
            int length = numTasks();
            for(int i = 0; i< length; i++)
            {
                if(Constant.tasks.get(i).taskId == id)
                {
                    return i;
                }
            }
        }
        

        return -1;
    }

    // TASKS START HERE

    @POST
    @Path("add-task")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTask(TaskDto taskDto) {

        taskDto.taskId = Constant.tasks.size() + 1;
        Constant.tasks.add(taskDto);

        List<Object> objectListTasks = new ArrayList<>(Constant.tasks); // type casting here from type TaskDto to type Object                                                                
        Common.writeInFile(objectListTasks, "TASKS"); // writeInFile used here

        return Response.ok(Constant.tasks).build();
    }

    @DELETE
    @Path("/deletetasks/{id}")
    public Response deleteTask(@PathParam("id") int id) {
        //System.out.println("Entered del method");
        if(findById(id, "tasks")!=-1)
        {
            TaskDto taskToDelete = Constant.tasks.get(findById(id, "tasks"));
            boolean removed = false;
            
            
            removed = Constant.tasks.remove(taskToDelete);
            

            if(removed)
            {
                return Response.ok().build();
            }
        }
        
        return Response.status(Response.Status.BAD_REQUEST).build();
       
    }

    @GET
    @Path("/tasks")
    public List<TaskDto> tasksAvailable() {
        return Constant.tasks;
    }

    @GET
    @Path("/tasks/{id}")
    public Response tasksAvailable(@PathParam("id") int id) {
        TaskDto taskDto = new TaskDto();
        if (id >= 1 && id <= Constant.tasks.size()) {
            taskDto = Constant.tasks.get(id - 1);
        }
        return Response.ok(taskDto).build();
    }
    
    @GET
    @Path("/tasks/size")
    public int numTasks(){
        return Constant.tasks.size();
    }
    


    // PRODUCTS
     
    @POST
    @Path("/add-product")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductDto productDto) {

        productDto.productId = Constant.products.size() + 1;
        Constant.products.add(productDto);

        List<Object> objectListTasks = new ArrayList<>(Constant.products); // type casting here from type TaskDto to type Object                                                                
        Common.writeInFile(objectListTasks, "PRODUCTS"); // writeInFile used here

        return Response.ok(Constant.products).build();
    }

    @GET
    @Path("/products")
    public List<ProductDto> productsAvailable() {
        return Constant.products;
    }

    @GET
    @Path("/products/{id}")
    public Response productsAvailable(@PathParam("id") int id) {
        ProductDto productDto = new ProductDto();
        if (id >= 1 && id <= Constant.products.size()) {
            productDto = Constant.products.get(id - 1);
        }
        return Response.ok(productDto).build();
    }
    
    @GET
    @Path("/products/size")
    public int numProducts(){
        return Constant.products.size();
    }

    @POST
    @Path("/add-project")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addproject(ProjectDto projectDto) {

        projectDto.projectId = Constant.projects.size() + 1;
        Constant.projects.add(projectDto);

        List<Object> objectListTasks = new ArrayList<>(Constant.projects); // type casting here from type TaskDto to type Object                                                                
        Common.writeInFile(objectListTasks, "projectS"); // writeInFile used here

        return Response.ok(Constant.projects).build();
    }




    @GET
    @Path("/projects")
    public List<ProjectDto> projectsAvailable() {
        return Constant.projects;
    }

    @GET
    @Path("/projects/{id}")
    public Response projectsAvailable(@PathParam("id") int id) {
        ProjectDto projectDto = new ProjectDto();
        if (id >= 1 && id <= Constant.projects.size()) {
            projectDto = Constant.projects.get(id - 1);
        }
        return Response.ok(projectDto).build();
    }
    
    @GET
    @Path("/projects/size")
    public int numprojects(){
        return Constant.projects.size();
    }

    @Path("/upload/{name}")
    @POST
    @Consumes({"application/pdf", "text/plain"})
    //@Produces({"text/plain,application/pdf"})
    //@Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response fileUpload(File file, @PathParam("name") String name) throws IOException {
        try {
            System.out.println("Path:" + file.getAbsolutePath());
            System.out.println("Name:" + file.getName());
            File dstDirFile = new File("Quarkus/Installed_File/src/main/resources/"+name);
            //File rename = new File("Quarkus/Installed_File/src/main/resources/"+name+".txt");
            //boolean flag = file.renameTo(rename);
            System.out.println("After rename:" + file.getName());
            FileUtils.copyFileToDirectory(file, dstDirFile);
            return Response.ok(Constant.tasks).build();
        } catch (Exception e) {
            // TODO: handle exception
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        


        // File copied = new File("src/test/resources/copiedWithIo.txt");
        // try (
        // InputStream in = new BufferedInputStream(
        //     new FileInputStream(original));
        // OutputStream out = new BufferedOutputStream(
        //     new FileOutputStream(copied))) {
    
        //     byte[] buffer = new byte[1024];
        //     int lengthRead;
        //     while ((lengthRead = in.read(buffer)) > 0) {
        //         out.write(buffer, 0, lengthRead);
        //         out.flush();
        //     }
        
    }

    // @POST
    // @Consumes(MediaType.MULTIPART_FORM_DATA)
    // public Response uploadPdf(@MultipartForm FormData formData) {
    //     try {
    //         savePdf(formData.getPdf(), "path/to/save/pdf.pdf");
    //         return Response.ok().build();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         return Response.serverError().build();
    //     }
    // }

    // private void savePdf(InputStream inputStream, String filePath) throws IOException {
    //     byte[] buffer = new byte[inputStream.available()];
    //     inputStream.read(buffer);
    //     FileOutputStream outputStream = new FileOutputStream(new File(filePath));
    //     outputStream.write(buffer);
    //     outputStream.close();
    // }
}

