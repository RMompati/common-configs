# Common Configurations

> In this repository is the common configurations that I have accross most of my Spring Boot applications.

## SMTP config
> Under the `smtp` package are the configurations for the simple SMTP configurations using gmail.
> ### The classes/interfaces:
> - #### Interfaces:
>   - `SMTPEmailCredentials` - The interface that has methods for defining the `gmail` credentiaks such as `username` and `password` used when configuring the `JavaMailSender`.
>   - `SMTPEmailService` - The interface defining the `sendEmail` method and its variations.
> - #### Classes:
>   - `SMTPEmailConfig` - A configuration class with a `@Bean` method that configures the `JavaMailSender`.
>   - `SMTPEmailConfig2` - A configuration class with a `@Bean` method that points to the implementation of `SMTPEmailService`.
>   - `SMTPEmailInfo` - A helper class that groups the basic email properties such as the **subject**, **recipient(s)**, **body**, *etc*.

## Using this.
> ### Clone, Install, and add as a dependency
>   Clone this and in `common-configs/` run `mvn clean install`.
>
>   Add as a dependency:
> ```XML
>		<dependency>
>			<groupId>com.eroldmr</groupId>
>			<artifactId>common-configs</artifactId>
>			<version>0.0.1</version>
>		</dependency>
> ```

> **Note**
> The interface `SMTPEmailCredentials` needs to be implemented in your project and a `@Bean` method that points to the implementation needs to be defined. An example is shown below:

### Implementing and Configuring SMTPEmailCredentials:

> `SMTPEmailCredentialsImpl.java`
```Java
package com.example.app.messaging.email;

import com.eroldmr.common.configs.smtp.SMTPEmailCredentials;

public class SMTPEmailCredentialsImpl implements SMTPEmailCredentials {
  @Override
  public String username() { return "username@gmail.com"; }

  @Override
  public String password() { return "password"; }

  @Override
  public Boolean debug() { return true; }
}
```

> `AppConfig.java`
```Java
...
  @Bean
  SMTPEmailCredentials smtpCredentials() {
    return new SMTPEmailCredentialsImpl();
  }
...
```

> **OR simply**
> 
> `AppConfig.java`
```Java
...
  @Bean
  SMTPEmailCredentials smtpCredentials() {
    return new SMTPEmailCredentials() {
      @Override
      public String username() { return "username@gmail.com"; }

      @Override
      public String password() { return "password"; }

      @Override
      public Boolean debug() { return true; }
    };
  }
...
```
### Final configurations:
> In order for our Spring Boot application to pick up our `@Service SMTPEmailService` and `@Configuration` we add:
> ```Java
> @ComponentScan(basePackages = {"com.eroldmr.common.configs", "com.example.app"})
> ````
> to the main class of our app.


*Happy hacking...*
