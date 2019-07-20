-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2019 at 06:34 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `society2`
--

-- --------------------------------------------------------

--
-- Table structure for table `daily_staff`
--

CREATE TABLE `daily_staff` (
  `id` bigint(20) NOT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `residentid` bigint(20) DEFAULT NULL,
  `societyid` bigint(20) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `contactno` varchar(50) DEFAULT NULL,
  `alternatecontact` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `streetno` varchar(70) DEFAULT NULL,
  `streetname` varchar(70) DEFAULT NULL,
  `city` varchar(70) DEFAULT NULL,
  `postalcode` varchar(20) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `age` double DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `colone` varchar(255) DEFAULT NULL,
  `coltwo` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daily_staff`
--

INSERT INTO `daily_staff` (`id`, `profile`, `residentid`, `societyid`, `firstname`, `lastname`, `contactno`, `alternatecontact`, `email`, `streetno`, `streetname`, `city`, `postalcode`, `country`, `province`, `age`, `gender`, `picture`, `status`, `colone`, `coltwo`) VALUES
(1, 'Driver', 1, 2, 'Yogesh', 'Lokare', '7218030984', '8459967003', 'yogesh.lokare@tejovat.com', 'PLOT NO 19', 'MG Road', 'Pune', '411019', 'India', 'Maharashtra', 25, 'M', 'http://10.0.0.92:8080/tbsm/api/auth/upload/5?type=resident', 'Y', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `residents`
--

CREATE TABLE `residents` (
  `id` bigint(20) NOT NULL,
  `age` double DEFAULT NULL,
  `alternatecontact` varchar(50) DEFAULT NULL,
  `apartment` varchar(70) DEFAULT NULL,
  `colone` varchar(255) DEFAULT NULL,
  `coltwo` varchar(255) DEFAULT NULL,
  `contactno` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `flatnumber` varchar(70) DEFAULT NULL,
  `floornumber` varchar(70) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `loginallowed` bit(1) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `societyid` bigint(20) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `residents`
--

INSERT INTO `residents` (`id`, `age`, `alternatecontact`, `apartment`, `colone`, `coltwo`, `contactno`, `email`, `firstname`, `flatnumber`, `floornumber`, `gender`, `lastname`, `loginallowed`, `password`, `picture`, `societyid`, `status`, `token`, `username`) VALUES
(1, 25, '9632587415', 'Samata', NULL, NULL, '7218030984', 'ajay.katware@tejovat.com', 'Ajay', '303', '3', 'M', 'Katware', b'0', '12345678', NULL, 2, 'Y', NULL, 'ajay.katware@tejovat.com'),
(2, 24, '7894561236', 'Samata', NULL, NULL, '9876543215', 'swapnil.ghadage@tejovat.com', 'Swapnil', '304', '3', 'M', 'Ghadage', b'0', '12345678', NULL, 2, 'Y', NULL, 'swapnil.ghadage@tejovat.com'),
(3, NULL, '211212', '211212', '', '', '9763336834', 'Swapnil5400@gmail.com', 'Swapnil', '1', '1', 'M', 'Ghadage', NULL, '12345678', 'http://localhost:8090/api/auth/upload/3?type=resident', 2, 'Y', '', 'Swapnil'),
(4, NULL, '9545885400', '9545885400', '', '', '9763336834', 'Ajay@gmail.com', 'Aj123', '3', '2', 'M', 'Aj321', NULL, '12345678', 'http://localhost:8090/api/auth/upload/4?type=resident', 2, 'Y', '', 'Ajay1'),
(5, NULL, '1234567898', '1234567898', '', '', '1234567893', 'Yogesh@gmail.com', 'Yogesh', '3', '2', 'M', 'Lokare', NULL, 'bAnsGGOkta/vzCJc5+7Liw==', 'http://localhost:8090/api/auth/upload/5?type=resident', 2, 'Y', '', 'Yogesh'),
(6, NULL, '1234567898', '1', '', '', '1234567893', 'yogeshlokare12@gmail.com', 'Yogesh', '3', '2', 'M', 'Lokare', NULL, 'VTNxo6eUd8Vax5+9gL9XBw==', 'http://10.0.0.92:8080/tbsm/api/auth/upload/5?type=resident', 2, 'Y', '', 'yogeshlokare12@gmail.com'),
(7, NULL, '1234567898', '1', '', '', '1234567893', 'swapnilghadge02@gmail.com', 'Yogesh', '3', '2', 'M', 'Lokare', NULL, 'K/RovP/R/xXO6ypY4WS+yw==', 'http://10.0.0.92:8080/tbsm/api/auth/upload/5?type=resident', 2, 'Y', '', 'swapnilghadge02@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `status` char(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `description`, `name`, `status`) VALUES
(1, 'This is Tejovat Admin Role', 'Tejovat Admin', 'Y'),
(2, 'This is Society Admin Role', 'Society Admin', 'Y'),
(3, 'This is Security User role', 'Network Admin', 'Y'),
(4, 'This is role', 'Role 1', 'N'),
(5, 'This is daily staff', 'Daily staff', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `security_user`
--

CREATE TABLE `security_user` (
  `id` bigint(20) NOT NULL,
  `apartment` varchar(70) DEFAULT NULL,
  `city` varchar(70) DEFAULT NULL,
  `colone` varchar(255) DEFAULT NULL,
  `coltwo` varchar(255) DEFAULT NULL,
  `contactno` varchar(50) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `loginallowed` bit(1) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `postalcode` varchar(20) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `rating` varchar(10) DEFAULT NULL,
  `societyid` bigint(20) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `streetname` varchar(70) DEFAULT NULL,
  `streetno` varchar(70) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `role` bigint(20) DEFAULT NULL,
  `alternatecontact` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `security_user`
--

INSERT INTO `security_user` (`id`, `apartment`, `city`, `colone`, `coltwo`, `contactno`, `country`, `dob`, `email`, `firstname`, `gender`, `lastname`, `loginallowed`, `password`, `picture`, `postalcode`, `province`, `rating`, `societyid`, `status`, `streetname`, `streetno`, `token`, `username`, `role`, `alternatecontact`) VALUES
(1, 'A', 'Pune', NULL, '8459967003', '7218030984', 'India', '2019-05-13 18:30:00', 'kiran.labade@tejovat.com', 'Kiran', 'M', 'Labade', b'0', '12345678', 'http://10.0.0.92:8080/tbsm/api/auth/upload/1?type=securityuser', '411058', 'Maharashtra', '1', 2, 'Y', 'Plot no 1 B', 'Headwind Road Pune', NULL, 'kiran.labade@tejovat.com', 3, NULL),
(2, 'A', 'Pune', NULL, NULL, '9876543215', 'India', '2019-05-15 18:30:00', 'vishakha.patil@tejovat.com', 'Vishakha', 'F', 'Patil', b'0', '987654321', 'http://10.0.0.92:8080/tbsm/api/auth/upload/2?type=securityuser', '411058', 'Maharashtra', '5', 2, 'Y', NULL, NULL, NULL, 'vishakha.patil@tejovat.com', 3, NULL),
(3, '', '', '', '', 'FFFFFFFFFFF', '', '2019-05-14 18:30:00', 'BBBBBBBBBBB', 'CCCCCCCC', 'M', 'DDDDDDDDD', NULL, '12345678', 'http://10.0.0.92:8080/tbsm/api/auth/upload/3?type=securityuser', '', '', '', 2, 'Y', '', '', '', 'AAAAAAAAAAAAAA', 3, NULL),
(4, '', '', '', '', 'RRRRRRRRRR', '', '2019-05-14 18:30:00', 'YYYYYYYYYYYY', 'SWAP', 'M', 'PPPPPPPP', NULL, '12345678', 'http://10.0.0.92:8080/tbsm/api/auth/upload/4?type=securityuser', '', '', '', 2, 'N', '', '', '', 'XXXXXXXXXX1', NULL, NULL),
(5, '', '', '', '', '4554', '', '2019-06-10 18:30:00', 'Akurdi Railway Station', 'Pune', 'M', 'ccccc', NULL, '12345678', '', '', '', '', 2, 'N', '', '', '', '303, Gurudwar Chowk', 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `society_master`
--

CREATE TABLE `society_master` (
  `id` bigint(20) NOT NULL,
  `address_one` varchar(70) DEFAULT NULL,
  `address_two` varchar(70) DEFAULT NULL,
  `apartments` int(11) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `contact_no` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `email` varchar(70) NOT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `phone_no` varchar(45) DEFAULT NULL,
  `postal_code` varchar(20) DEFAULT NULL,
  `society_code` varchar(70) DEFAULT NULL,
  `society_name` varchar(70) NOT NULL,
  `state` varchar(45) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `society_master`
--

INSERT INTO `society_master` (`id`, `address_one`, `address_two`, `apartments`, `city`, `contact_no`, `country`, `email`, `fax`, `phone_no`, `postal_code`, `society_code`, `society_name`, `state`, `status`, `picture`) VALUES
(1, '304, Whitesquare', 'Hinjewadi road', 1, 'Pune', '7745002255', 'India', 'info@tejovat.com', NULL, '7218030984', '41105', 'SOC001', 'Tejovat Building Security Management', 'Maharashtra', 'Y', 'http://10.0.0.92:8080/tbsm/api/auth/upload/1?type=society'),
(2, 'Plot No 1B', 'Sector No 19', 1, 'Pune', '8459967003', 'India', 'yogeshlokare12@gmail.com', NULL, '7218030984', '411019', NULL, 'J J Chambers', 'Maharashtra', 'Y', 'http://10.0.0.92:8080/tbsm/api/auth/upload/2?type=society'),
(3, '4554', '454', 65, '545', '5456456564', 'Iceland', 'info@qubix.com', '6454', '4564564', '5454', NULL, 'Qubix Park', 'Austurland', 'Y', 'http://10.0.0.92:8080/tbsm/api/auth/upload/3?type=society'),
(4, '205, Akurdi', '', 3, 'Pune', '9623568961', 'India', 'vedanta@gmail.com', '', '', '411035', NULL, 'Vedanta', 'Maharashtra', 'Y', '');

-- --------------------------------------------------------

--
-- Table structure for table `society_users`
--

CREATE TABLE `society_users` (
  `id` bigint(20) NOT NULL,
  `apartment` varchar(70) DEFAULT NULL,
  `city` varchar(70) DEFAULT NULL,
  `colone` varchar(255) DEFAULT NULL,
  `coltwo` varchar(255) DEFAULT NULL,
  `contactno` varchar(50) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `loginallowed` bit(1) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `postalcode` varchar(20) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `rating` varchar(10) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `streetname` varchar(70) DEFAULT NULL,
  `streetno` varchar(70) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `societyid` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `society_users`
--

INSERT INTO `society_users` (`id`, `apartment`, `city`, `colone`, `coltwo`, `contactno`, `country`, `dob`, `email`, `firstname`, `gender`, `lastname`, `loginallowed`, `password`, `picture`, `postalcode`, `province`, `rating`, `status`, `streetname`, `streetno`, `token`, `username`, `societyid`) VALUES
(1, 'A', 'Pune', NULL, NULL, '7218030984', 'India', '1986-05-04 00:00:00', 'yogesh.lokare@tejovat.com', 'Yogesh', 'M', 'Lokare', b'1', 'JmV0O6ymEaWrEdg9LmHKYQ==', 'http://10.0.0.92:8080/tbsm/api/auth/upload/2?type=user', '411019', 'Maharashtra', '1', 'Y', 'J J Cahmbers, Pune', '19', '6534e565-e7ed-418a-a01e-bfcb391848a0', 'yogesh.lokare@tejovat.com', 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `contactno` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `city` varchar(70) DEFAULT NULL,
  `postalcode` varchar(20) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `coltwo` varchar(255) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `rating` varchar(10) DEFAULT NULL,
  `apartment` varchar(70) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `loginallowed` bit(1) DEFAULT NULL,
  `streetname` varchar(70) DEFAULT NULL,
  `streetno` varchar(70) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `role` bigint(20) DEFAULT NULL,
  `colone` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `contactno`, `email`, `username`, `dob`, `gender`, `city`, `postalcode`, `country`, `province`, `coltwo`, `password`, `picture`, `rating`, `apartment`, `status`, `loginallowed`, `streetname`, `streetno`, `token`, `role`, `colone`) VALUES
(1, 'Santosh', 'Labade', '7745002288', 'santosh.labade@tejovat.com', 'santosh.labade@tejovat.com', '1986-05-04 00:00:00', 'M', 'Pune', '411019', 'India', 'Maharashtra', NULL, 'UuymsyR6DMmisQxJ+QGz1Q==', 'http://10.0.0.92:8080/tbsm/api/auth/upload/1?type=user', '1', 'A', 'Y', b'1', 'Whitesqaure, Hinjewadi', '303', NULL, 1, NULL),
(2, 'Yogesh', 'Lokare', '7218030984', 'yogesh.lokare@tejovat.com', 'yogesh.lokare@tejovat.com', '1986-05-04 00:00:00', 'M', 'Pune', '411019', 'India', 'Maharashtra', NULL, 'wgO5kd31fksJcwXZnyBudQ==', 'http://10.0.0.92:8080/tbsm/api/auth/upload/2?type=user', '1', 'A', 'Y', b'1', 'J J Cahmbers, Pune', '19', NULL, 2, NULL),
(3, 'Kiran ', 'Labade', '123321123', 'Hello@tejovat.com', 'Hello@tejovat.com', '2019-05-17 18:30:00', 'F', '411058', '693258', 'Iceland', 'Austurland', '', 'nHJDTJsAnX3LdDmlX/bTIg==', 'http://10.0.0.92:8080/tbsm/api/auth/upload/3?type=user', '', 'Whitesquare', 'Y', NULL, 'Hinjewadi road', '303', '', 3, ''),
(4, 'Raj', 'Kappor', '545645', 'raj.kappor@qubix.com', 'raj.kappor@qubix.com', '2019-05-17 18:30:00', 'M', 'Pune', '411058', 'Belgium', 'Antwerpen', '', '1q/upxPtqz7z6dzilY3WfQ==', 'http://10.0.0.92:8080/tbsm/api/auth/upload/4?type=user', '', 'Sayali', 'Y', NULL, 'Pune Mumbai Highway', 'Plot No 1 B, Sector 30', '', 2, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `daily_staff`
--
ALTER TABLE `daily_staff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `residents`
--
ALTER TABLE `residents`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKs8hrs9q7srj7our7jewdv6fgp` (`username`),
  ADD UNIQUE KEY `UKfdbh184txvda5ox0fabw9lrdt` (`email`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `security_user`
--
ALTER TABLE `security_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKo716pk0dv59lgrnslah3ngqtd` (`username`),
  ADD UNIQUE KEY `UK9docx1j9f19looi7evs8k88x1` (`email`),
  ADD KEY `FKo78ihig8oxwlwuxlnbh728er7` (`role`);

--
-- Indexes for table `society_master`
--
ALTER TABLE `society_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `society_users`
--
ALTER TABLE `society_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKe7sgoputy94hpsv91p4mmyvbr` (`username`),
  ADD UNIQUE KEY `UKofmljd0thw4e3xvkqgi5nfd6l` (`email`),
  ADD KEY `FK7yyubv88p0yvxi7ima2k0pnki` (`societyid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD KEY `FK20wcxq3dnh6io9qug4vc90p0p` (`role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `daily_staff`
--
ALTER TABLE `daily_staff`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `residents`
--
ALTER TABLE `residents`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `security_user`
--
ALTER TABLE `security_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `society_master`
--
ALTER TABLE `society_master`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `society_users`
--
ALTER TABLE `society_users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
