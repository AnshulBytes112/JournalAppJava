# 📓 Journal App Backend

A secure and scalable **Journal Management Backend** built with **Java Spring Boot**, providing APIs for both **users** and **admins** to manage journal entries and journeys.  
It features **JWT-based authentication**, **role-based access control**, and **Apache Kafka** for event-driven messaging.

---

## 🚀 Features

### User Features
- ✍ **Create Journal Entries** – Add personal notes or thoughts.
- 📂 **View Journal Entries** – Retrieve all entries created by the logged-in user.
- 🛠 **Update Entries** – Edit existing entries.
- 🗑 **Delete Entries** – Remove entries as needed.
- 📜 **Create & View Journeys** – Group related entries into journeys.

### Admin Features
- 👥 **Manage Users** – View and manage user accounts.
- 📊 **Monitor Activities** – Access logs and events from Kafka topics.
- 🗂 **View All Entries** – Retrieve entries across all users.

---

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot 3**
  - Spring Web (REST APIs)
  - Spring Data JPA
  - Spring Security (JWT Authentication)
- **Apache Kafka** (event-driven architecture)
- **MongoDB** (data storage)
- **Maven** (build tool)

---

## 📦 Getting Started

### Prerequisites
- **Java 17+**
- **Maven**
- **MongoDB**
- **Apache Kafka & Zookeeper**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/AnshulBytes112/JournalAppJava.git
   cd JournalAppJava
