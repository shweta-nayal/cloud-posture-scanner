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
│
├── frontend/
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

Add credentials in:

```properties
src/main/resources/application.properties
```

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

Developed as part of a Cloud Security Assignment.
