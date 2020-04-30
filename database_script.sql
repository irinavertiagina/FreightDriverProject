-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2020 at 11:16 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `admin_last_name` varchar(50) NOT NULL,
  `admin_first_name` varchar(50) NOT NULL,
  `admin_contact` varchar(50) NOT NULL,
  `admin_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `admin_last_name`, `admin_first_name`, `admin_contact`, `admin_password`) VALUES
(1, 'higgs', 'vertiagin', 'higgs@gmail.com', 'password'),
(16, 'Doe', 'Joel', 'jd@mail.com', 'pass'),
 

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `cusromer_id` int(11) NOT NULL,
  `customer_last_name` varchar(30) DEFAULT NULL,
  `customer_first_name` varchar(30) DEFAULT NULL,
  `customer_contact` varchar(30) DEFAULT NULL,
  `customer_password` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cusromer_id`, `customer_last_name`, `customer_first_name`, `customer_contact`, `customer_password`) VALUES
(1, 'Missha', 'Higgins', 'abc@mail.com', 'password');

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

CREATE TABLE `driver` (
  `driver_id` int(11) NOT NULL,
  `driver_last_name` varchar(50) NOT NULL,
  `driver_first_name` varchar(50) NOT NULL,
  `driver_contacts` varchar(50) NOT NULL,
  `manager_id` varchar(11) NOT NULL,
  `driver_password` varchar(50) NOT NULL,
  `current_assignment_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`driver_id`, `driver_last_name`, `driver_first_name`, `driver_contacts`, `manager_id`, `driver_password`, `current_assignment_id`) VALUES
(3, 'Cat', 'Higgs', 'mycat@gmail.com', '1', 'qwerty', 26),
(5, 'Snegurenk', 'Alek', 'alek89@mail.ru', '1', 'pass', 0);

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `manager_id` int(11) NOT NULL,
  `manager_last_name` varchar(50) NOT NULL,
  `manager_first_name` varchar(50) NOT NULL,
  `manager_contact` varchar(50) NOT NULL,
  `manager_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`manager_id`, `manager_last_name`, `manager_first_name`, `manager_contact`, `manager_password`) VALUES
(3, 'John', 'Gor', 'contact@mail.com', 'password'),
(8, 'Buggins', 'Oleg', 'obug@gmail,ru', 'pass');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `order_start_date` varchar(30) DEFAULT NULL,
  `order_cargo` varchar(50) DEFAULT NULL,
  `order_destination` varchar(50) DEFAULT NULL,
  `order_location` varchar(30) DEFAULT NULL,
  `vehicle_id` int(11) DEFAULT NULL,
  `order_status_id` int(30) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  `order_finish_date` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`order_id`, `driver_id`, `customer_id`, `order_start_date`, `order_cargo`, `order_destination`, `order_location`, `vehicle_id`, `order_status_id`, `manager_id`, `order_finish_date`) VALUES
(12, 0, 3, '2020-202-20', 'cargocargp', 'fff', 'fff', NULL, NULL, 3, NULL),
(26, 0, 1, '2020-02-02', 'my cats', 'Chicago', 'NY', 1, 0, 1, '2020-02-02'),
(37, 1, 1, '2020-12-12', 'ice cream boxes', 'Montreal', 'Montana', 1, 0, 4, '2020-12-11'),
(39, 0, 1, '2020-05-05', 'Cat\'s toys', 'Ohio, IL', 'Aurora, IL', 1, 3, 4, '2020-02-02'),
(46, 0, NULL, 'null', ' null', 'null', 'null', 1, 0, 4, 'order_finish_date'),
(47, 0, 1, '', ' ', '', '', 1, 0, 4, 'order_finish_date'),
(48, 2, 3, '2020-05-05', ' furniture', 'Ann Arbor, MI', 'Troy, MI', 1, 3, 4, '2020-05-05');

-- --------------------------------------------------------

--
-- Table structure for table `order_status`
--

CREATE TABLE `order_status` (
  `order_status_id` int(11) NOT NULL,
  `order_status_description` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_status`
--

INSERT INTO `order_status` (`order_status_id`, `order_status_description`) VALUES
(0, 'need more information'),
(1, 'ready to shipment'),
(2, 'in transit'),
(3, 'done');

-- --------------------------------------------------------

--
-- Table structure for table `testtable`
--

CREATE TABLE `testtable` (
  `first` int(11) NOT NULL,
  `second` int(11) NOT NULL,
  `third` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testtable`
--

INSERT INTO `testtable` (`first`, `second`, `third`) VALUES
(1, 2, 3),
(1, 2, 3),
(1, 2, 3),
(1, 2, 3),
(1, 2, 3),
(1, 2, 3),
(1, 2, 3),
(1, 2, 3),
(1, 2, 3),
(1, 2, 3),
(1, 2, 3),
(1, 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `vehicle_id` int(11) NOT NULL,
  `vehicle_service_date` varchar(30) DEFAULT NULL,
  `vehicle_status` varchar(30) DEFAULT NULL,
  `vehicle_specs` varchar(30) DEFAULT NULL,
  `driver_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`vehicle_id`, `vehicle_service_date`, `vehicle_status`, `vehicle_specs`, `driver_id`) VALUES
(7, '2020-02-02', 'info', '0', 3),
(8, '2222-10-02', 'new information ', '0', 4),
(12, 'today', 'stayus', 'specs', 0),
(13, 'today', 'stayus', 'specs', 0),
(14, ' 2020-20-20', 'stayus', 'small van', 18);

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_status`
--

CREATE TABLE `vehicle_status` (
  `status_id` int(11) NOT NULL,
  `status_desc` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vehicle_status`
--

INSERT INTO `vehicle_status` (`status_id`, `status_desc`) VALUES
(0, 'in the garage'),
(1, 'in transit'),
(2, 'on service'),
(3, 'road accident');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cusromer_id`);

--
-- Indexes for table `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`driver_id`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`manager_id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `order_status`
--
ALTER TABLE `order_status`
  ADD PRIMARY KEY (`order_status_id`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`vehicle_id`);

--
-- Indexes for table `vehicle_status`
--
ALTER TABLE `vehicle_status`
  ADD PRIMARY KEY (`status_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `cusromer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `driver`
--
ALTER TABLE `driver`
  MODIFY `driver_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `manager`
--
ALTER TABLE `manager`
  MODIFY `manager_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `vehicle_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
