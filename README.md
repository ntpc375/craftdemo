# craftdemo

 1. Create a springboot app (spring web, lombok, devtools, jpa, mysql)
    - controller, repository, entity, service, application.properties
    
 2. Mysql create schema
 
 3. application.properties
 
    > server.port=8081
    > 
    > spring.jpa.hibernate.ddl-auto=update
    > spring.datasource.url=jdbc:mysql://localhost:3306/listings_schema?useSSL=false
    > #spring.datasource.url=jdbc:mysql://34.67.27.190:3306/listings_schema?useSSL=false
    > spring.datasource.username=raki
    > spring.datasource.password=mysql100
    > spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    > ##spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    
    application-docker.properties
    server.port=8081
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://host.docker.internal:3306/listings_schema?useSSL=false
    #spring.datasource.url=jdbc:mysql://34.67.27.190:3306/listings_schema?useSSL=false
    spring.datasource.username=raki
    spring.datasource.password=mysql100
    #spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    
    application-minikube.properties
    server.port=8081
    spring.jpa.hibernate.ddl-auto=update
    #spring.datasource.url=jdbc:mysql://host.minikube.internal:3306/listings_schema?useSSL=false
    spring.datasource.url=jdbc:mysql://34.67.27.190:3306/listings_schema?useSSL=false
    spring.datasource.username=root
    spring.datasource.password=mysql100
    #spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

 3. Dockerfile
    FROM adoptopenjdk/openjdk11:alpine-jre
    ADD target/restapis-0.0.1-SNAPSHOT.jar app.jar
    ENV PROFILE=docker
    ENTRYPOINT ["java","-jar", "app.jar", "--spring.profiles.active=${PROFILE}"]
    #ENTRYPOINT ["java","-jar", "app.jar"]
    
 4. Build docker file
     docker build -t restapis:1.0 .    
     
 5. Run docker file 
     docker run -p 8081:8081 -it restapis:2.0 --env PROFILE=docker
     
 6. Create deployment.yml 
    
    kind: Service
    apiVersion: v1
    metadata:
      name: rambo
      labels:
        name: rambo
    spec:
      ports:
        - nodePort: 30002
          port: 8081
          targetPort: 8081
          protocol: TCP
      selector:
        app: rambo
      type: NodePort

    ---
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: rambo
    spec:
      selector:
        matchLabels:
          app: rambo
      replicas: 3
      template:
        metadata:
          labels:
            app: rambo
        spec:
          containers:
            - name: rambo
              image: rambo:3.0
              env:
                - name: PROFILE
                  value: minikube
              ports:
                - containerPort: 8081
          hostAliases:
            - ip: 192.168.64.1
              hostnames:
                - host.minikube.internal

 6. Run docker in minikube kubernetes
    docker run -p 8080:8080 -it --env PROFILE=minikube rambo:2.0
     
     - eval $(minikube docker-env)  
     - mvn clean install
     - docker build -t restapis:1.0 .
     
     kubectl apply -f deployment.yml
     kubectl get pods
     kubectl get cluster-info
     kubectl get deployments
     
     
        docker run -p 9999:9999 -it --env PROFILE=minikube localboot:1.0
     
 
 

