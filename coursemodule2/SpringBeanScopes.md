# Spring Bean Scopes

The **scope** of a bean defines what type of object the Spring IoC container must build for the bean defined.

While defining the bean in the spring configuration file(for example, **beans.xml**), we may specify the **scope** of the bean.

Spring offers 5 scopes for a bean by default; however, we may customize and build custom scopes.

Spring supports the following types of bean scopes out of the box.

1. Singleton: Per Spring container, return a single bean object. By default, all the beans defined in spring will have **singleton** scope
2. Prototype: When the Spring container is requested, return a new bean instance each time.
3. Request: Returns a single bean object per **HTTP request** and is only valid in the context of a web-aware Spring ApplicationContext, which implies only in the web application.
4. Session: Returns a single bean object per **HTTP session** and is only valid in the context of a web-aware Spring ApplicationContext, which implies only in the web application.
5. GlobalSession: Return a single bean instance per global HTTP session. In portlet applications, session scope is considered as global session scope. Hence only valid when used in a portlet context