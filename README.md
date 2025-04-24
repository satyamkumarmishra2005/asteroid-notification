# ☄️ Asteroid Collision Alert System

A distributed backend system built with Spring Boot that fetches real-time asteroid data from NASA and sends alerts to users about potentially hazardous asteroids using asynchronous microservices.

## 📁 Project Structure

```
.
├── asteroidalerting/         # Fetches asteroid data from NASA and publishes alerts
├── notificationservice/      # Consumes alerts and sends email notifications
└── README.md
```

## 🚀 Tech Stack

| Technology       | Purpose                                      |
|------------------|----------------------------------------------|
| **Spring Boot 3**     | Backend framework for building services     |
| **Java 21**           | Programming language                        |
| **Apache Kafka**      | Asynchronous message communication          |
| **Spring Mail**       | Sending alert emails                        |
| **NASA NeoWs API**    | Source of real-time asteroid data           |

## 🛠️ Service Responsibilities

### `asteroidalerting/`
- Fetches data from the **NASA NeoWs API**
- Detects **potentially hazardous asteroids**
- Publishes alert messages to **Kafka**

### `notificationservice/`
- Listens to Kafka topic for alerts
- Sends **email notifications** to users
- Logs each alert for tracking

## 🔧 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/satyamkumarmishra2005/asteroid-notification.git
cd asteroid-alert-system
```

### 2. Configure Properties

Create `application.properties` for both services (these are ignored in `.gitignore`):

```bash
# For asteroidalerting
cp asteroidalerting/src/main/resources/application-example.properties asteroidalerting/src/main/resources/application.properties

# For notificationservice
cp notificationservice/src/main/resources/application-example.properties notificationservice/src/main/resources/application.properties
```

Update with your own:
- NASA API key
- Mail SMTP credentials

### 3. Start Kafka (if using locally)

Use Docker or your local Kafka installation. Example with Docker:

```bash
docker-compose up -d
```

### 4. Run the Services

```bash
# In one terminal
cd asteroidalerting
./mvnw spring-boot:run

# In another terminal
cd notificationservice
./mvnw spring-boot:run
```

## 🧪 Sample Email Alert

```
🚨 Asteroid Alert 🚨
Name: 2025 AB
Approach Date: 2025-04-24
Velocity: 30,000 km/h
Miss Distance: 450,000 km

Take precautionary measures and stay updated!
```


## 📜 License

This project is open source and available under the [MIT License](LICENSE).

## 🙌 Acknowledgements

- [NASA Open APIs](https://api.nasa.gov/)
- Spring Boot & Kafka Community

## 💬 Feedback & Contributions

Pull requests and feedback are welcome! Feel free to fork and enhance the project.
