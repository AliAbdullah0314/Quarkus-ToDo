package com.bahl.resources;

import java.util.ArrayList;
import java.util.List;

import com.bahl.dto.PersonDto;
import com.bahl.dto.ProductDto;
import com.bahl.dto.ProjectDto;
import com.bahl.dto.TaskDto;
import com.bahl.util.Common;
import com.bahl.util.Constant;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/to-do-list")
public class MainResourcePerson {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome() {
        return "Welcome to the ToDoList";
        // return Response.ok(persons).build();
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
        return Constant.persons.size();
    }

    @POST
    @Path("/add-person")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(PersonDto newPerson) {
        newPerson.id = Constant.persons.size() + 1; // the id is related to the size, first Constant.persons id will be
                                                    // 1 and so on
        Constant.persons.add(newPerson);
        List<Object> objectListPersons = new ArrayList<>(Constant.persons);
        Common.writeInFile(objectListPersons, "PEOPLES");

        // return Response.ok(Constant.persons.get(0).id).build();
        return Response.ok(Constant.persons).build();
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
    @Path("tasks/size")
    public int numTasks(){
        return Constant.tasks.size();
    }
    


    // PRODUCTS
     
    @POST
    @Path("add-product")
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
    @Path("products/size")
    public int numProducts(){
        return Constant.products.size();
    }

    @POST
    @Path("add-project")
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
    @Path("projects/size")
    public int numprojects(){
        return Constant.projects.size();
    }
}
