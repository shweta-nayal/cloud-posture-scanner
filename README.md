# Cloud Posture Scanner

A lightweight AWS Cloud Security Posture Scanner built using:

- Spring Boot (Backend)
- React + Vite (Frontend)
- AWS SDK for Java
- DynamoDB
- EC2 & S3 Scanning

The project scans AWS resources, performs CIS benchmark security checks, stores results securely, and displays everything on a frontend dashboard.

---

# Features

## AWS Resource Discovery

### EC2 Instances
Displays:
- Instance ID
- Instance Type
- Region
- Public IP
- Security Groups

### S3 Buckets
Displays:
- Bucket Name
- Region
- Encryption Status
- Public/Private Access

---

# CIS AWS Benchmark Checks

Implemented checks include:

- No public S3 buckets
- S3 buckets encrypted
- Root account MFA enabled
- CloudTrail enabled
- Security Groups not open to 0.0.0.0/0 for SSH/RDP

---

# Frontend Dashboard

The React dashboard displays:

- Total EC2 instances
- Total S3 buckets
- CIS pass/fail summary
- EC2 table
- S3 table
- Security findings

---

# Tech Stack

## Backend
- Java
- Spring Boot
- Gradle
- AWS SDK v2

## Frontend
- React
- Vite
- Axios
- CSS

## AWS Services
- EC2
- S3
- DynamoDB
- IAM
- CloudTrail

---

# Project Structure

```bash
cloud-posture-scanner/
│
├── backend/
│   └── Dockerfile
│
├── frontend/
│   └── Dockerfile
│
├── docker-compose.yml
|
├── .env
│
└── README.md
```

---

# Backend Setup

## 1. Navigate to backend

```bash
cd backend
```

## 2. Configure AWS Credentials

AWS credentials can be configured either using:
- application.properties (local development)
- .env file (Docker deployment)

Example:

```properties
aws.accessKey=YOUR_ACCESS_KEY
aws.secretKey=YOUR_SECRET_KEY
aws.region=ap-east-1
```

---

## 3. Run Backend

```bash
./gradlew bootRun
```

Backend runs on:

```bash
http://localhost:8080
```

---

# Frontend Setup

## 1. Navigate to frontend

```bash
cd frontend
```

## 2. Install dependencies

```bash
npm install
```

## 3. Start frontend

```bash
npm run dev
```

Frontend runs on:

```bash
http://localhost:5173
```

---

# API Endpoints

| Endpoint | Description |
|---|---|
| `/scan` | Run AWS scan |
| `/instances` | Get EC2 instances |
| `/buckets` | Get S3 buckets |
| `/cis-results` | Get CIS benchmark results |
| `/health` | Backend health check |

---

# How It Works

1. Frontend triggers `/scan`
2. Backend connects to AWS using AWS SDK
3. EC2 and S3 resources are discovered
4. CIS security checks are executed
5. Results are stored in DynamoDB
6. Frontend dashboard displays findings

---

# Sample Security Findings

- Public S3 bucket detection
- Unencrypted bucket detection
- Open SSH/RDP security groups
- Missing MFA
- CloudTrail disabled

---

# Docker Support

The project is fully containerized using Docker.

Both frontend and backend run inside separate Docker containers using Docker Compose.
The application can also be run in a production-like environment using Docker Compose.

---

# Running with Docker

## 1. Create `.env` file

Create a `.env` file in the project root:

```env
AWS_ACCESS_KEY=YOUR_ACCESS_KEY
AWS_SECRET_KEY=YOUR_SECRET_KEY
AWS_REGION=YOUR_REGION
```

---

## 2. Build and Run Containers

```bash
docker-compose up --build
```

---

# Services

| Service | Port |
|---|---|
| Frontend | 5173 |
| Backend | 8080 |

---

# Access Application

## Frontend

```bash
http://localhost:5173
```

## Backend

```bash
http://localhost:8080
```

---

# Dockerized Architecture

```text
React Frontend Container
            ↓
Spring Boot Backend Container
            ↓
AWS SDK
            ↓
AWS Services (EC2, S3, DynamoDB)
```

---

# Production Ready Features

- Dockerized frontend and backend
- Environment variable based AWS configuration
- Secure credential handling
- Modular backend architecture
- REST API based communication
- Scalable deployment support

---

# Design Decisions

- Spring Boot used for scalable REST API development
- React used for responsive dashboard UI
- AWS SDK v2 used for AWS resource discovery
- DynamoDB used for secure storage of scan findings
- Docker used for containerized deployment
- Modular service-based architecture used for maintainability and scalability

---

Developed as part of a Cloud Security Assignment.
