# Java Threading & Socket Programming Projects

A collection of Java projects demonstrating the evolution of a web server from a single-threaded implementation to a scalable thread-pooled architecture.

Features:
- TCP Socket Programming
- Single-threaded Server Architecture
- Multi-threaded Request Handling
- Thread Pool Implementation using ExecutorService
- File Transfer using Buffered Streams
- Load Testing with Apache JMeter

## Projects Included

### 1. SingleThreaded_WebServer

A basic TCP server that handles one client connection at a time.

#### Concepts Covered

* TCP Socket Programming
* ServerSocket and Socket APIs
* Blocking I/O
* Request/Response Communication
* Sequential Client Processing

#### Limitations

* Only one client can be served at a time.
* Additional clients must wait until the current request is completed.

---

### 2. Multithreaded

An improved server implementation where each client connection is handled by a separate thread.

#### Concepts Covered

* Java Threads
* Concurrent Client Handling
* Runnable Tasks
* Thread Lifecycle Management

#### Benefits

* Multiple clients can be served simultaneously.
* Improved responsiveness compared to a single-threaded server.

#### Limitations

* Creating a new thread per request can become expensive under heavy load.

---

### 3. ThreadPool

A production-style implementation that uses a fixed-size thread pool to manage client requests efficiently.

#### Concepts Covered

* ExecutorService
* Fixed Thread Pools
* Task Queuing
* Resource Management
* Concurrent File Transfer

#### Features

* Configurable thread pool size.
* File transfer from server to client.
* Better scalability compared to creating a new thread for every request.

---

## Technologies Used

* Java
* TCP/IP Sockets
* Multithreading
* Executor Framework
* Buffered Streams

---

## Project Structure

```text
Java_Threading/
├── SingleThreaded_WebServer/
├── Multithreaded/
└── ThreadPool/
```

---

## Running the Projects

### Compile

```bash
javac Server.java
```

### Run

```bash
java Server
```

The server starts listening on:

```text
localhost:8010
```

---

## Key Learnings

* Difference between single-threaded and concurrent servers.
* How operating systems manage socket connections.
* Thread creation and scheduling in Java.
* Advantages of thread pools over creating threads manually.
* Efficient file transfer using buffered streams.
* Building the foundations of a web server.

---

## Load Testing

The servers were tested using Apache JMeter to simulate concurrent client connections and evaluate performance under load.

### Test Setup

* Tool: Apache JMeter
* Protocol: TCP
* Server Port: 8010
* Multiple virtual users were configured to connect simultaneously.
* Response times and error rates were monitored during testing.

### Objectives

* Compare the behavior of single-threaded, multi-threaded, and thread-pooled servers.
* Observe request handling under concurrent load.
* Identify bottlenecks caused by sequential processing.
* Validate improved throughput using multithreading and thread pools.

### Observations

* The SingleThreaded_WebServer processes one client at a time, causing requests to queue under load.
* The Multithreaded server improves concurrency by assigning a dedicated thread per client.
* The ThreadPool server provides controlled concurrency and better resource utilization by reusing worker threads.

### Key Learning

Load testing highlighted the trade-offs between simplicity, scalability, and resource consumption across different server architectures.

