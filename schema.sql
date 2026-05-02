-- Hotel Management System Database Schema
-- Run this script in MySQL to initialise the `hms` database.

CREATE DATABASE IF NOT EXISTS hms;
USE hms;

-- ─────────────────────────────────────────────
-- Auth Service
-- ─────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS login (
    id       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,   -- BCrypt hash
    role     VARCHAR(20)  NOT NULL DEFAULT 'STAFF'
);

-- ─────────────────────────────────────────────
-- Room Service
-- ─────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS room (
    id           BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    room_number  VARCHAR(10)    NOT NULL UNIQUE,
    availability VARCHAR(20)    NOT NULL DEFAULT 'Available',
    clean_status VARCHAR(20)    NOT NULL DEFAULT 'Clean',
    price        DECIMAL(10, 2) NOT NULL,
    bed_type     VARCHAR(30)    NOT NULL
);

-- ─────────────────────────────────────────────
-- Customer Service
-- ─────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS customer (
    id          BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_type     VARCHAR(30)    NOT NULL,
    id_number   VARCHAR(50)    NOT NULL,
    name        VARCHAR(100)   NOT NULL,
    gender      VARCHAR(10)    NOT NULL,
    country     VARCHAR(60)    NOT NULL,
    room_number VARCHAR(10)    NOT NULL,
    status      VARCHAR(50)    NOT NULL DEFAULT 'Checked-In',
    deposit     DECIMAL(10, 2)
);

-- ─────────────────────────────────────────────
-- Employee Service
-- ─────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS employee (
    id      BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(100)   NOT NULL,
    age     INT            NOT NULL,
    gender  VARCHAR(10)    NOT NULL,
    job     VARCHAR(60)    NOT NULL,
    salary  DECIMAL(10, 2) NOT NULL,
    phone   VARCHAR(15)    NOT NULL,
    aadhar  VARCHAR(20)    NOT NULL,
    email   VARCHAR(100)   NOT NULL
);

-- ─────────────────────────────────────────────
-- Driver Service
-- ─────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS driver (
    id        BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    age       INT          NOT NULL,
    gender    VARCHAR(10)  NOT NULL,
    company   VARCHAR(100) NOT NULL,
    brand     VARCHAR(50)  NOT NULL,
    available VARCHAR(20)  NOT NULL DEFAULT 'Yes',
    location  VARCHAR(100) NOT NULL
);
