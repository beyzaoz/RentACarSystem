public class Created {
    String url = "jdbc:postgresql://localhost:5432/car_show_room";
    String dbUser = "car_show_room";
    String dbPassword = "";

    String Created= """
            CREATE TABLE car(
            id SERIAL PRIMARY KEY,
            vehicletype VARCHAR(50) NOT NULL;
            vehiclemodel VARCHAR(150) NOT NULL,
            CarPlateNumber VARCHAR(150) NOT NULL,
            vehicleavailable VARCHAR(50) NOT NULL,
            dailyprice numeric(10,2) NOT NULL,
            hourlyPrice numeric(10,2) NOT NULL,
            weeklyPrice numeric(10,2) NOT NULL,
            monthlyPrice numeric(10,2) NOT NULL,
            createdDate date DEFAULT current_date,
            updatedDate date DEFAULT current_date,
      
            );
            
            CREATE TABLE user(
            id SERIAL PRIMARY KEY,
            first_name VARCHAR(150) NOT NULL,
            last_name VARCHAR(150) NOT NULL,
            email VARCHAR(50) NOT NULL,
            password VARCHAR(50) NOT NULL,
            driver_license VARCHAR(50) NOT NULL,
            birth_date date NOT NULL,
            roles VARCHAR(50) NOT NULL,
            createdDate date DEFAULT current_date,
            updatedDate date DEFAULT current_date,
            
            );
            
            CREATE TABLE reservation (
            id SERIAL PRIMARY KEY,
            customer_id INT REFERENCES customer(id),
            ca_id INT REFERENCES car(id),
            start_date date,\s
            car_available VARCHAR(50),
            end_date date,\s
            createdDate date DEFAULT current_date,
            updatedDate date DEFAULT current_date
            
            );
            
            
            CREATE TABLE payment(
            id SERIAL PRIMARY KEY,
            reservation_id INT REFERENCES reservation(id),
            payment_date date,
            total_amount numeric(10,2),
            payment_method VARCHAR(50),
            payment_status VARCHAR(50),
            createdDate date DEFAULT current_date,
            updatedDate date DEFAULT current_date
            );
            
            
            CREATE TABLE carcategory (
            id SERIAL PRIMARY KEY,\s
            category_name VARCHAR(50),\s
            createdDate date DEFAULT current_date,
            updatedDate date DEFAULT current_date
            
            );
            
             CREATE TABLE vehicle(
                        id SERIAL PRIMARY KEY,
                        vehicletype VARCHAR(50) NOT NULL,
                        vehicleavailable VARCHAR(50) NOT NULL,
                        dailyprice numeric(10,2) NOT NULL,
                        hourlyPrice numeric(10,2) NOT NULL,
                        weeklyPrice numeric(10,2) NOT NULL,
                        monthlyPrice numeric(10,2) NOT NULL,
                        createdDate date DEFAULT current_date,
                        updatedDate date DEFAULT current_date
                        );
                        
              CREATE TABLE motorcycle(
                         id SERIAL PRIMARY KEY,
                         vehicletype VARCHAR(50) NOT NULL,
                         vehicleavailable VARCHAR(50) NOT NULL,
             			motortype VARCHAR(50) NOT NULL,
             			mplatenumber VARCHAR(50) NOT NULL,
                         dailyprice numeric(10,2) NOT NULL,
                         hourlyPrice numeric(10,2) NOT NULL,
                         weeklyPrice numeric(10,2) NOT NULL,
                         monthlyPrice numeric(10,2) NOT NULL,
                         createdDate date DEFAULT current_date,
                         updatedDate date DEFAULT current_date
                         );
             CREATE TABLE helicopter(
                        id SERIAL PRIMARY KEY,
                        vehicletype VARCHAR(50) NOT NULL,
                        vehicleavailable VARCHAR(50) NOT NULL,
            			serialnumber VARCHAR(50) NOT NULL,
            			purpose VARCHAR(50)
                        dailyprice numeric(10,2) NOT NULL,
                        hourlyPrice numeric(10,2) NOT NULL,
                        weeklyPrice numeric(10,2) NOT NULL,
                        monthlyPrice numeric(10,2) NOT NULL,
                        createdDate date DEFAULT current_date,
                        updatedDate date DEFAULT current_date
                        );
            
            
            
            
            
            
            
            """;


}
