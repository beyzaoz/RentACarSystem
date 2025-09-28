package dao.constant;

public class SqlScriptConstant {


    public static final String VEHICLE_IS_AVAILABLE = """
    SELECT COUNT(*) FROM reservation
    WHERE vehicle_id = ?
      AND (start_date_time < ? AND end_date_time > ?)
      AND status = 'ACTIVE'
""";

    private SqlScriptConstant(){

    }
    public static final String SAME_ID_ORDER_CANCEL = """
            SELECT COUNT(*)\s
            FROM reservation\s
            WHERE vehicleid = ?\s
              AND statue = 'ACTIVE'
              AND startdatetime <= ?\s
              AND enddatetime >= ?;
            """;


    //LISTING SQLs
    public static final String ALL_LISTED = """
            SELECT id, vehicletype, vehicle_model, hourlyprice, dailyprice, weeklyprice, monthlyprice, vehicleavailable,
                   vehicleplatenumber, NULL serialnumber, NULL motortype, NULL mplatenumber, NULL purpose
            FROM car
            UNION ALL
            SELECT id, vehicletype, vehicle_model, hourlyprice, dailyprice, weeklyprice, monthlyprice, vehicleavailable,
                   NULL vehicleplatenumber, serialnumber, NULL motortype, NULL mplatenumber, purpose
            FROM helicopter
            UNION ALL
            SELECT id, vehicletype, vehicle_model, hourlyprice, dailyprice, weeklyprice, monthlyprice, vehicleavailable,
                   NULL vehicleplatenumber, NULL serialnumber, motortype, mplatenumber, NULL purpose
            FROM motorcycle
            ORDER BY id asc
            LIMIT ? OFFSET ?;
            """;

    public static final String COUNT_RECORD_NUMBER = """
            SELECT * FROM vehicle
            """;

    //SEARCHING SQLs
    public static final String FIND_OLD_ORDERS = """
            SELECT * FROM reservation WHERE customer_id = ?;
            """;

    public static final String SEARCH_VEHICLE_BY_ID_HELICOPTER = """
             SELECT * FROM helicopter WHERE UPPER(vehicletype) = UPPER(?)
             """;
    public static final String SEARCH_VEHICLE_BY_ID_CAR = """
             SELECT * FROM car WHERE UPPER(vehicletype) = UPPER(?)
             """;
    public static final String SEARCH_VEHICLE_BY_ID_MOTORCYCLE = """
             SELECT * FROM motorcycle WHERE UPPER(vehicletype) = UPPER(?)
             """;

    //SEARCHING SQLs
    public static final String SEARCH_VEHICLE_BY_BRAND_HELICOPTER = """
             SELECT * FROM helicopter WHERE UPPER(vehicle_model) = UPPER(?)
             """;
    public static final String SEARCH_VEHICLE_BY_BRAND_CAR = """
             SELECT * FROM car WHERE UPPER(vehicle_model) = UPPER(?)
             """;
    public static final String SEARCH_VEHICLE_BY_BRAND_MOTORCYCLE = """
             SELECT * FROM motorcycle WHERE UPPER(vehicle_model) = UPPER(?)
             """;


    //SAVING SQLs
    public static final String PAYMENT_SAVE = """
            INSERT INTO payment(id, customer_id, vehicle_id, start_date_time, end_date_time, orderdate_total_amount)
           VALUES (?, ?, ?, ?, ?, ?);
            """;

    public static final String USER_SAVE = """
            INSERT INTO "users" (first_name, last_name, email, password, driver_license, birth_date, roles)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

    public static final String ORDER_SAVE = """
            INSERT INTO reservation(customer_id,vehicle_id,start_date_time,end_date_time,orderdate,total_amount)
            VALUES(?,?,?,?,?,?)
            """;

    public static final String ORDER_DELETE = """
            UPDATE reservation SET status = ? WHERE id = ?
            """;

    public static final String CAR_VEHICLE_SAVE = """
             INSERT INTO car ( vehicle_model, vehicletype, hourlyprice, dailyprice, weeklyprice, monthlyprice,
             created_by, updated_by, vehicleplatenumber, vehicleavailable, vehicle_price)
             VALUES (?,?,?,?,?,?,?,?,?,?,?)
             """;


    public static final String MOTORCYCLE_VEHICLE_SAVE = """
             INSERT INTO motorcycle ( vehicle_model, vehicletype, hourlyprice, dailyprice, weeklyprice, monthlyprice,
             created_by, updated_by, motortype, mplatenumber, vehicleavailable,vehicle_price)
             VALUES (?,?,?,?,?,?,?,?,?,?,?,?)
             """;

    public static final String HELICOPTER_VEHICLE_SAVE = """
             INSERT INTO helicopter (vehicle_model, vehicletype, hourlyprice, dailyprice, weeklyprice, monthlyprice,
             created_by, updated_by, serialnumber, purpose, vehicleavailable,vehicle_price)
             VALUES (?,?,?,?,?,?,?,?,?,?,?,?)
             """;

    //--------

    // FINDING SQLs
    public static final String USER_EXIST_EMAIL = """
            SELECT * FROM "users" WHERE LOWER(email) = LOWER(?) ;
            """;

     public static final String FIND_VEHICLE_BY_ID_HELICOPTER = """
             SELECT * FROM helicopter WHERE id = ?
             """;
    public static final String FIND_VEHICLE_BY_ID_CAR = """
             SELECT * FROM car WHERE id = ?
             """;
    public static final String FIND_VEHICLE_BY_ID_MOTORCYCLE = """
             SELECT * FROM motorcycle WHERE id = ?
             """;

    //DELETING SQLs
    public static final String CAR_VEHICLE_DELETE = """
    DELETE FROM car WHERE id = ?
    """;

    public static final String MOTORCYCLE_VEHICLE_DELETE = """
    DELETE FROM motorcycle WHERE id = ?
    """;

    public static final String HELICOPTER_VEHICLE_DELETE = """
    DELETE FROM helicopter WHERE id = ?
    """;


}
