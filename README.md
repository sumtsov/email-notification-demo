# Email Notification Demo
Sample project that demonstrates how to send email notifications using Java and Spring Boot

## How to run
1. To run application with your own smtp server properties adjust application.yml file:
```
spring:
  mail:
    host: smtp.gmail.com
    username: [your google email]
    password: [your google account app password]
    port: 587
    properties:
      mail.smtp.auth: true
      mail.smtp.starttls.enable: true

app:
  email:
    recipients: [your recipients]
    from: [your google email]
```
2. Build the project: `mvn clean install`
3. Run the .jar: `java -jar email-notification-demo-${version}.jar`

## Usage
Send notification:
```
curl -X 'POST' \
  'http://localhost:8080/email/send' \
  -H 'accept: */*' \
  -d ''
```

## API Reference
- swagger-ui: `http://localhost:8080/swagger-ui/index.html`