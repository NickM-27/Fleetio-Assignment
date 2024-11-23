# Fleetio Project

![img](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3nFeescQKHUzhZA6JxgxQTehp4llrIpBjsA&s)

This is a project built for Fleetio hiring assignment. It pulls data from the Fleetio API and:

- Main Page
  - Displays vehicles with relevant information and image
  - Supports endless scrolling using the paging API
  - Supports filtering by name and by current vehicle status
- Details Page
  - Displays extra details about the vehicle including mileage, VIN, License Plate, and current operator if applicable
  - Display comments (currently displays all comments as I was unable to figure out how to filter comments to a specific vehicle)

If I had more time I would have liked to do the following:
- Fix comments to display only for selected vehicle
- Implement Unit and Integration Testing

The app was built using `Android Studio Ladybug | 2024.2.1 Patch 2` and is set to compile and target API 35.

The UI is built using Jetpack Compose and Kotlin Coroutines.