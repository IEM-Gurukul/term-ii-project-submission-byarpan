# 🚗 Ride-Sharing Simulation System

**PCCCS495 – Term II Project** |
**Student:** Arpan Ghosh | **Roll No:** 06 | **Dept:** CSE (IoTCSBT) | **Spring 2026**

---

## Project Title

**Ride-Sharing Simulation System with Dynamic Dispatch and Fare Computation**

---

## Problem Statement

Urban commuters frequently struggle to find nearby drivers quickly, obtain transparent fare estimates, and receive real-time updates during their journey. Existing local transport systems lack proper coordination between riders and drivers, leading to inefficient trip management and poor user experience. This project simulates the complete lifecycle of a ride-sharing platform — from driver and rider registration, automatic driver-rider matching via a dispatch algorithm, dynamic fare computation using interchangeable pricing strategies, to real-time status notifications for both parties. All trip and driver data is persisted to local files across sessions, demonstrating a fully functional, object-oriented simulation of a real-world system.

---

## Target User

Daily commuters, office goers, and independent drivers in urban and semi-urban areas who need a fast, transparent, and organized ride-booking experience.

---

## Core Features

- 🧍 Rider and Driver registration with profile management
- 🔍 Automatic driver-rider matching via nearest-available dispatch algorithm
- 💰 Dynamic fare calculation — Flat, Per-Km, and Surge pricing strategies
- 📢 Real-time trip status notifications to both Rider and Driver
- 📋 Complete trip history with persistent file-based storage
- ⚠️ Robust exception handling for unavailable drivers, invalid inputs, and fare errors
- 🖥️ Full Java Swing GUI with multiple panels and smooth navigation

---

## OOP Concepts Used

- **Abstraction:** Abstract `User` class defines shared behavior for `Driver` and `Rider`. `IFareStrategy` interface abstracts all fare computation logic.
- **Inheritance:** `Driver` and `Rider` both extend the abstract `User` class, inheriting common identity attributes while adding domain-specific fields.
- **Polymorphism:** `FlatFareStrategy`, `PerKmFareStrategy`, and `SurgeFareStrategy` all implement `IFareStrategy` — runtime strategy switching without modifying `RideService`.
- **Exception Handling:** Custom exceptions — `DriverNotFoundException`, `InvalidLocationException`, `FareCalculationException` — handle domain-specific failures gracefully.
- **Collections:** `ArrayList` for managing drivers and riders; `HashMap` for tracking active trips; `ArrayList` for trip history.

---

## Proposed Architecture Description

The system follows a layered object-oriented architecture with clear separation of concerns across five layers. The abstract `User` class forms the base, extended by `Driver` and `Rider`. A central `RideService` coordinates the full trip lifecycle — request, dispatch, fare computation, and completion. The `FareStrategy` interface enables runtime selection of pricing models via the Strategy Pattern. The Observer Pattern decouples the notification system from business logic, ensuring automatic updates to both Rider and Driver on every trip status change. A dedicated Repository layer handles all file-based persistence.

```
User (Abstract)
    ├── Driver
    └── Rider
            ↓
        Trip ←→ Location · TripStatus
            ↓
    RideService ──→ DispatchService
         │               │
         ├──→ IFareStrategy (Strategy Pattern)
         │        ├── FlatFareStrategy
         │        ├── PerKmFareStrategy
         │        └── SurgeFareStrategy
         │
         └──→ TripObserver (Observer Pattern)
                  ├── RiderNotifier
                  └── DriverNotifier
                        ↓
              DriverRepository · TripRepository
                        ↓
                data/drivers.txt · data/trips.txt
```

---

## How to Run

### Prerequisites

- - Java JDK 17 or above (compiled with --release 17 flag for compatibility)
- Git

### Step 1 — Clone the Repository

```bash
git clone https://github.com/IEM-Gurukul/term-ii-project-submission-byarpan.git
cd term-ii-project-submission-byarpan
```

### Step 2 — Create Output Directory

```bash
mkdir out
```

### Step 3 — Compile

```bash
javac -d out src/com/rideshare/model/*.java src/com/rideshare/exception/*.java src/com/rideshare/strategy/*.java src/com/rideshare/observer/*.java src/com/rideshare/dispatcher/DispatchService.java src/com/rideshare/repository/*.java src/com/rideshare/service/*.java src/com/rideshare/ui/*.java
```

### Step 4 — Run

```bash
javac --release 17 -d out src/com/rideshare/model/*.java ...
```

### Windows — Quick Run (double click)

```
run.bat
```

### Usage Order

```
1. Register as Driver   →  Enter driver details
2. Register as Rider    →  Enter rider details
3. Book a Ride          →  Select fare type, enter distance
4. View Trip History    →  See all completed trips
```

---

## Package Structure

```
src/com/rideshare/
├── model/          →  User, Driver, Rider, Trip, Location, TripStatus
├── strategy/       →  IFareStrategy, FlatFare, PerKmFare, SurgeFare
├── observer/       →  TripObserver, RiderNotifier, DriverNotifier
├── dispatcher/     →  DispatchService
├── repository/     →  DriverRepository, TripRepository
├── service/        →  RideService, UserService
├── exception/      →  DriverNotFoundException, InvalidLocationException, FareCalculationException
└── ui/             →  MainFrame, HomePanel, DriverPanel, RegisterRiderPanel, BookRidePanel, TripHistoryPanel
```

---

## Git Discipline Notes

> Minimum 10 meaningful commits required.
> Commits must show incremental development.
> Development must span multiple days.
> Final submission must not be a single bulk upload.

| #   | Commit Message                                                                  |
| --- | ------------------------------------------------------------------------------- |
| 1   | set up complete package structure for the ride sharing project                  |
| 2   | added User as abstract base class along with Driver Rider and Location models   |
| 3   | added Trip TripStatus models along with custom exception classes                |
| 4   | implemented Strategy Pattern with IFareStrategy and all fare strategy classes   |
| 5   | implemented Observer Pattern with TripObserver RiderNotifier and DriverNotifier |
| 6   | added Repository layer with file based persistence for drivers and trips        |
| 7   | added RideService and UserService to handle core business logic                 |
| 8   | added complete Swing UI with all panels and navigation                          |
| 9   | added UML class diagram to docs folder                                          |
| 10  | final project cleanup and documentation update                                  |

---

## Project Structure

```
## Project Structure

term-ii-project-submission-byarpan/
├── src/          →  Java source files
├── docs/         →  UML diagram
├── report/       →  Project report PDF
├── slides/       →  Presentation slides
└── README.md     →  This file
```

---

_Ride-Sharing Simulation System — PCCCS495 Term II Project — Spring 2026_
