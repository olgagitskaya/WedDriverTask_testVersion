package com.epam;

import com.epam.bean.Person;
import com.epam.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.*;

import java.text.DecimalFormat;

public class TestWD
    {
        private Steps steps;
        private final static String LOGIN = "mashkevich.nastya@mail.ru";
        private final static String PASSWORD = "wertyu1";
        private final static String CITY_OF_DEPARTURE = "Warsaw";
        private final static String CITY_OF_ARRIVAL = "Barcelona";
        private final static String SERVICE_COUNTRY = "Italy";
        private final static String DATE_OF_FLIGHT = "5/April";
        private final static String DATE_FORWARD = "2/April";
        private final static String DATE_BACK = "14/May";
        private final static String FULL_FORMAT_FLIGHT_DATE = "05/04/2017";
        private final static String FLIGHT_NUMBER = "8845";
        private final static int NUMBER_OF_PASSENGERS = 2;
        private final static String CITY = "Madrid";
        private final static int FLIGHT_WITH_RETURN = 2;

        @BeforeTest(description = "Init browser")
        public void setUp()
        {
            steps = new Steps();
            steps.initBrowser();
        }

        @Test
        public void oneCanLoginGithub()
        {
            steps.loginToVueling(LOGIN, PASSWORD);
            Assert.assertTrue(steps.isLoginToVueling());
        }

        @Test
        public void oneCanChooseServiceCenter ()
        {
            Assert.assertTrue(steps.isChangeContactInfo(SERVICE_COUNTRY));
        }

        @Test
        public void oneCanSearchFlight ()
        {
            steps.startWorkWithMainPage(CITY_OF_DEPARTURE, CITY_OF_ARRIVAL, DATE_FORWARD);
            Assert.assertTrue(steps.checkFlight(DATE_BACK));
        }

        @Test
        public void oneCanFillInfoAboutPassenger ()
        {
            Person person = new Person("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");
            steps.startWorkWithMainPage(CITY_OF_DEPARTURE, CITY_OF_ARRIVAL, DATE_FORWARD);
            steps.fillPassengerInformation(person,DATE_BACK);
            Assert.assertTrue(steps.isFillInfoCorrect(person));
        }

        @Test
        public void oneCanCheckStatusFlight ()
        {
            steps.startWorkWithFlightsStatusPageWithFlightNumber(FLIGHT_NUMBER, DATE_OF_FLIGHT);
            Assert.assertEquals(steps.checkFlightStatus(),("Not operating"));
            Assert.assertEquals(steps.checkDateFlight(), DATE_OF_FLIGHT);
            Assert.assertEquals(steps.checkCityDeparture (), CITY_OF_DEPARTURE);
            Assert.assertEquals(steps.checkCityArrival (), CITY_OF_ARRIVAL);
        }

        @Test
        public void oneCanCheckRightPriceForTwoPassengerFlightOneWay ()
        {
            steps.canChooseFlightOneWay(CITY_OF_DEPARTURE, CITY_OF_ARRIVAL, DATE_FORWARD);
            Assert.assertEquals((steps.takePriceFromWebSiteFor1Passenger()*NUMBER_OF_PASSENGERS),steps.takeTotalPriceForAllPassenger());
        }

        @Test
        public void oneCanCheckRightPriceForTwoPassengerFlightWithReturn ()
        {
            steps.canChooseFlightWithReturn(CITY_OF_DEPARTURE, CITY_OF_ARRIVAL, DATE_FORWARD, DATE_BACK);
            double priceWithoutFee = steps.takePriceFromWebSiteFor1Passenger()*NUMBER_OF_PASSENGERS;
            double bookingFee = steps.takeBookingFee();
            double actualPrice = priceWithoutFee + bookingFee;
            DecimalFormat df = new DecimalFormat("#.##");
            Assert.assertEquals(df.format(actualPrice), df.format(steps.takeFinalPrice()));
        }

        @Test
        public void canFindAirport()
        {
            steps.checkAirport(CITY);
            Assert.assertTrue(steps.isAirportFound());
        }

        @Test
        public void oneCanCheckAddLuggage() {
            Person person = new Person("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");
            steps.startWorkWithMainPage(CITY_OF_DEPARTURE, CITY_OF_ARRIVAL, DATE_FORWARD);
            steps.addLuggage(person, DATE_BACK);
            Assert.assertEquals(steps.takePriceForPassengerWithLuggage(), steps.takePriceForPassengerWithLuggageFromTable());
        }

        @Test
        public void oneCanCheckAddSeat() throws InterruptedException {
            Person person = new Person("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");
            steps.startWorkWithMainPage(CITY_OF_DEPARTURE, CITY_OF_ARRIVAL, DATE_FORWARD);
            steps.addSeat(person, DATE_BACK);
            Assert.assertEquals(steps.takeTotalPriceForPassengerWithSeats() / FLIGHT_WITH_RETURN, steps.takePriceForPassengerWithSeatsFromTable());
        }

        @Test
        public void oneCanCheckStatusFlightByDestinations()
        {
            steps.startWorkWithFlightsStatusPageWithDestinations(CITY_OF_DEPARTURE, CITY_OF_ARRIVAL, DATE_OF_FLIGHT);
            if(steps.severalFlightsTableDisplayed())
            {
                Assert.assertTrue(steps.correctMultipleFlightsInfoDisplayed(CITY_OF_DEPARTURE,CITY_OF_ARRIVAL,FULL_FORMAT_FLIGHT_DATE));
            }
            else{
                Assert.assertEquals(steps.checkFlightStatus(),("Not operating"));
                Assert.assertEquals(steps.checkDateFlight(), DATE_OF_FLIGHT);
                Assert.assertEquals(steps.checkCityDeparture (), CITY_OF_DEPARTURE);
                Assert.assertEquals(steps.checkCityArrival (), CITY_OF_ARRIVAL);
            }
        }

        @Test
        public void oneCanFindHotels()  {
            Assert.assertTrue(steps.enterHotelParameters(CITY_OF_ARRIVAL));
            Assert.assertTrue(steps.isHotelsFound());
        }
        @Test
        public void oneCanFindCar() {
            steps.enterCarParameters(CITY_OF_ARRIVAL);
            Assert.assertTrue(steps.isMapWithCarOffersDispaly());
        }

        @AfterClass(description = "Stop Browser")
         public void stopBrowser()
         {
             steps.closeDriver();
         }

    }
