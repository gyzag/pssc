# Data Description

The sample data is a small part of real taxi trajectory data in Wuhan City on 2015-04-11. All data is collected by GPS on taxi every 10 seconds.

## Field Description

| Field            | Type       | Description               |
| ---------------- |:----------:| -------------------------:|
| CAR_ID           | string     | taxi id                   |
| TIME_STAMP       | string     | time stamp                |
| LONGITUDE        | float      | car longitude             |
| LATITUDE         | float      | car latitude              |
| DIRECTION        | int        | car direction             |
| SPEED            | int        | car speed                 |
| PASSENGER_STATUS | string     | Carrying passenger or not |

The field PASSENGER_STATUS has two values: **full and empty** , where full means the taxi carried passengers at the point while empty means not.