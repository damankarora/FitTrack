package com.example.exerciselogger.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Configuration(value = "DataSourceConfig")
public class    DataSourceConfig {

    @Value("${spring.datasource.creds.path}")
    private String usernamePath;


    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;


    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    @Value("${nutritionx.api.creds}")
    private String credFilePath;

    private String username;
    private String password;

    private String apiId;
    private String apiKey;



    @Bean
    public DataSource dataSource() {

        try{
            this.readValues();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.driverName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);

        return dataSource;
    }


    public void readValues() throws IOException {

        FileReader fileReader = new FileReader(this.usernamePath);
        BufferedReader reader = new BufferedReader(fileReader);

        this.username = reader.readLine();
        this.password = reader.readLine();
        reader.close();
        fileReader.close();

        fileReader = new FileReader(credFilePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        this.apiId = bufferedReader.readLine();
        this.apiKey = bufferedReader.readLine();
        bufferedReader.close();
        fileReader.close();

    }


    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
