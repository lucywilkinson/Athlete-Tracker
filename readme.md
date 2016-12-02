Athlete Tracker
CSCI 4448

Matt Uhlar, Alex Goodwin, Lucy Wilkinson, Dmytro Ryzhkov

This is the group project for CSCI 4448

Built with Java connected to a mySql database, using MVC.

Athlete Tracker is a system for companies that sponsor athletes in various sports.
It is a way for the companies to keep track of who they're sponsoring, and the
products that have been shipped or need to be shipped to the athlete.

Users consist of Admins, Athlets, and Warehouse Workers.

Admin:
Admins can view and edit the profiles of every other user, and can view and edit all the shipment and
product information.

Athletes:
Athletes can view and edit their own profiles, but not those of other users, and can view what products are
being shipped to them.

Warehouse Workers:
Warehouse workers can view and edit their own profiles, view the profiles of athletes,
and view shipment and product information.

To Run:
Connect to database -
1) Install and add the JDBC driver to the class path
2) Create and populate the DB (Creation script in database folder)
3) edit config.properties file in /Athlete-Tracker/src/models/config.properties if needed

Run Driver.java

Login using your credentials found in the database