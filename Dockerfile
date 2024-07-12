# Usa la imagen oficial de Microsoft Build de OpenJDK 17 
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu


ARG clientId
ARG secretId
ARG url
ARG tenanId



#Variables de entorno
ENV KVConfig__KVUrl=${url}
ENV KVConfig__TenantId=${tenanId}
ENV KVConfig__ClientId=${clientId}
ENV KVConfig__ClientSecretId=${secretId}


# Establece el directorio de trabajo en el contenedor 
WORKDIR /app

# Copia los archivos y directorios locales al contenedor
COPY src src/
COPY pom.xml .

# Actualiza la lista de paquetes
RUN apt-get update

# Instala wget
RUN apt-get install -y wget

# Descarga Maven
RUN wget https://downloads.apache.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.tar.gz -P /tmp

# Descomprime el archivo tar.gz
RUN tar xf /tmp/apache-maven-*.tar.gz -C /opt

# Actualiza la variable de entorno PATH para incluir la instalación de Maven
ENV PATH=${PATH}:/opt/apache-maven-3.8.8/bin
RUN mvn -f /app/pom.xml clean install
CMD ["java", "-jar", "target/consultas-0.0.1-SNAPSHOT.jar"]
