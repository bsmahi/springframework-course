<div align="center">
<img src="../images/SpringMVCLogo.png" alt="SpringMVC">
</div>

# Terminology for Spring MVC

Here's an overview of the key concepts in Spring MVC:

* **Model:** The model represents the application's data and business logic. It encapsulates the data of the application and offers methods for accessing and manipulating that data. The model in the Spring MVC environment is frequently made up of POJOs (Plain Old Java Objects) or entities reflecting the application's domain.
* **View:** The view is in charge of rendering the application's user interface. It displays the model's data to the user in a manner that is simple to understand and interact with. The view in Spring MVC is often developed with template technologies such as **JSP (JavaServer Pages), Thymeleaf, or FreeMarker**.
* **Controller:** The controller processes user requests and works as a liaison between the model and the display. It takes user input, activates the relevant business logic in the model, and chooses the suitable view to present the answer. The controller in Spring MVC is implemented as a Java class annotated with **@Controller** or **@RestController**, and it specifies methods that are mapped to certain URLs or URL patterns.
* **DispatcherServlet:** The **DispatcherServlet** is the heart of the Spring MVC framework. It receives all the incoming requests and acts as a central request handler. It analyzes the request URL, maps it to the appropriate Controller, and delegates the request for processing. The DispatcherServlet also manages other components like HandlerMappings, HandlerAdapters, ViewResolvers, etc., to complete the request-response cycle.
* **Handler Mapping:** HandlerMapping is in charge of routing incoming requests to the correct Controller depending on the request URL or other criteria. It assists the DispatcherServlet in determining which Controller should deal with the request.
* **Handler Adapter:** HandlerAdapter is responsible for invoking the appropriate method in the Controller based on the request. It acts as a bridge between the DispatcherServlet and the Controller.
* **View Resolver:** ViewResolver is responsible for resolving the logical view name returned by the Controller into an actual View implementation. It determines the appropriate view template (e.g., JSP, Thymeleaf) to render the response.
* **Data Binding:** Spring MVC provides powerful data binding capabilities that allow mapping incoming request parameters to the Model objects automatically. It simplifies the process of extracting data from the request and populating the Model.
* **Request Mapping:** The RequestMapping annotation is used to map the incoming requests to specific methods in the Controller. It can be applied at the class or method level, allowing fine-grained control over request handling.
* **Validation:** Spring MVC includes built-in support for data validation. It provides validation annotations like `@NotNull`, `@Size`, etc., which can be used to validate the incoming request data. Validation errors can be captured and handled appropriately.

These are some of the most important ideas in Spring MVC. You may use the Spring MVC framework to create strong and modular web apps by properly using these ideas.