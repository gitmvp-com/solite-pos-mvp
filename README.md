# Solite POS MVP

A simplified MVP version of [Solite-POS](https://github.com/denisyordanp/Solite-POS) - Android Point of Sale system built with Jetpack Compose and Clean Architecture.

## Features

✅ **Product Management**
- View list of products with prices
- Product images and descriptions

✅ **Order Taking**
- Add products to cart
- Adjust quantities
- Calculate total price
- Complete orders

✅ **Order History**
- View completed orders
- See order details and totals

## Tech Stack

- **Language**: Kotlin 1.7.10
- **UI**: Jetpack Compose with Material Design
- **Architecture**: Clean Architecture (MVVM)
- **Dependency Injection**: Dagger Hilt 2.44
- **Database**: Room 2.4.3
- **Async**: Kotlin Coroutines
- **Build**: Gradle with Kotlin DSL

## Project Structure

```
solite-pos-mvp/
├── app/              # Presentation layer (UI, ViewModels)
├── domain/           # Business logic layer (Use Cases)
├── data/             # Data layer (Repository, Room DB)
├── common/           # Shared utilities and models
└── buildSrc/         # Build configuration
```

## Architecture

This project follows **Clean Architecture** principles:

- **app**: Contains Compose UI screens and ViewModels
- **domain**: Contains use cases and business logic
- **data**: Contains Room database, DAOs, and repository implementations
- **common**: Shared models and utilities

## Getting Started

### Prerequisites

- Android Studio Flamingo or later
- JDK 8 or later
- Android SDK with API 33
- Minimum Android API 23 (Android 6.0)

### Build and Run

1. Clone the repository:
```bash
git clone https://github.com/gitmvp-com/solite-pos-mvp.git
```

2. Open the project in Android Studio

3. Sync Gradle files

4. Run the app on an emulator or physical device

## What's Different from Parent Project?

This MVP simplifies the original Solite-POS:

❌ **Removed Features**:
- User authentication and registration
- Multiple store branches
- Online backup to server
- Bluetooth printing
- Complex order status workflow
- Payment types
- Promo system
- Customer management
- Outcome tracking
- Firebase integration

✅ **Kept Core Features**:
- Product catalog
- Order creation
- Order history
- Offline-first with Room DB
- Clean Architecture pattern
- Same dependency versions

## Sample Data

The app comes with pre-populated sample products:
- Coffee - $3.50
- Tea - $2.50
- Sandwich - $5.00
- Cake - $4.00

## License

This is an educational MVP project inspired by [Solite-POS](https://github.com/denisyordanp/Solite-POS).

Original project is licensed under Creative Commons Attribution-NonCommercial 4.0 International License.
