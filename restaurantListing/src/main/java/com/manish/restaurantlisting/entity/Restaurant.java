package com.manish.restaurantlisting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;

}

/*
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
public class Restaurant {

    @Id // denotes primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) // I don't want to give id every time, but whenever
    //we give name, city, address or description Id will be automatically generated by Hibernate using
    //hibernate sequence
    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
*/

/*
Two table will be generated, one for restaurant and one for hibernate sequence who will be maintaining the
hibernate id for me.

Table 1> restaurant
Hibernate: create table restaurant (id integer not null, address varchar(255), city varchar(255), name varchar(255), restaurant_description varchar(255), primary key (id)) engine=InnoDB

Table 2> restaurant_seq
Hibernate: create table restaurant_seq (next_val bigint) engine=InnoDB
Hibernate: insert into restaurant_seq values ( 1 )



What all two tables create table restaurant.
So this was the restaurant entity.

And hence, because it is one entity, a new table is created with name is restaurant and ID name, address, city and restaurant description as its attributes.

Apart from that, I have made IDs generated value and ID since it is generated value, it is the task of Hibernate,
To give me autogenerated IDs whenever I add a new restaurant into the restaurant table.

How will it do that? How will it remember what last ID it has given to me?

So that is why it has created a table known as hibernate sequence(restaurant_seq table) and initiated it with value 1.
So now whenever I add any restaurant into this application, the ID automatically will be generated as 1.

I do not have to give it explicitly to the hibernate.
* */