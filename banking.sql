-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 11, 2023 at 12:44 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking`
--

-- --------------------------------------------------------

--
-- Table structure for table `amount`
--

CREATE TABLE IF NOT EXISTS `amount` (
  `amountID` int(11) NOT NULL,
  `clientID` int(11) NOT NULL,
  `balance` int(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `amount`
--

INSERT INTO `amount` (`amountID`, `clientID`, `balance`) VALUES
(1, 4, 4980),
(2, 1, 9900);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `clientID` int(11) NOT NULL,
  `FirstName` varchar(40) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `Telephone` int(10) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientID`, `FirstName`, `LastName`, `gender`, `Telephone`, `Email`) VALUES
(1, 'Elisa', 'Kwizera', 'male', 787647168, 'kwizeraelisa77@gmail.com'),
(4, 'Eria', 'Ntakirutimana', 'Male', 788674367, 'eria@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `credit`
--

CREATE TABLE IF NOT EXISTS `credit` (
  `creditID` int(11) NOT NULL,
  `clientID` int(11) NOT NULL,
  `credited` int(11) NOT NULL,
  `creditStatus` varchar(10) NOT NULL,
  `action` varchar(20) NOT NULL,
  `Date` varchar(25) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credit`
--

INSERT INTO `credit` (`creditID`, `clientID`, `credited`, `creditStatus`, `action`, `Date`) VALUES
(4, 1, 1000, 'DR', 'Debit', '2023-04-09T05:06:47.764'),
(8, 1, 10000, 'DR', 'Debit', '2023-04-09T12:50:54.368'),
(11, 1, 60000, 'CashOut', 'withdrow', '2023-04-09T23:52:32.604'),
(12, 1, 1000, 'DR', 'Debit', '2023-04-09T23:54:20.637'),
(14, 1, 2000, 'DR', 'Debit', '2023-04-10T01:05:26.135'),
(15, 1, 5000, 'DR', 'Debit', '2023-04-10T01:14:59.597'),
(16, 1, 20, 'DR', 'Debit', '2023-04-10T01:28:41.402'),
(17, 1, 1880, 'DR', 'Debit', '2023-04-10T04:03:07.297'),
(18, 4, 1000, 'DR', 'Debit', '2023-04-10T04:07:11.182');

-- --------------------------------------------------------

--
-- Table structure for table `debit`
--

CREATE TABLE IF NOT EXISTS `debit` (
  `debitID` int(11) NOT NULL,
  `clientID` int(11) NOT NULL,
  `debited` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `userID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `name`, `password`) VALUES
(1, 'kwizera', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `amount`
--
ALTER TABLE `amount`
  ADD PRIMARY KEY (`amountID`),
  ADD UNIQUE KEY `clientID` (`clientID`),
  ADD KEY `clientID_2` (`clientID`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`clientID`);

--
-- Indexes for table `credit`
--
ALTER TABLE `credit`
  ADD PRIMARY KEY (`creditID`),
  ADD KEY `clientID` (`clientID`);

--
-- Indexes for table `debit`
--
ALTER TABLE `debit`
  ADD PRIMARY KEY (`debitID`),
  ADD KEY `clientID` (`clientID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `amount`
--
ALTER TABLE `amount`
  MODIFY `amountID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `clientID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `credit`
--
ALTER TABLE `credit`
  MODIFY `creditID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `debit`
--
ALTER TABLE `debit`
  MODIFY `debitID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `amount`
--
ALTER TABLE `amount`
  ADD CONSTRAINT `amount_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `credit`
--
ALTER TABLE `credit`
  ADD CONSTRAINT `credit_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `debit`
--
ALTER TABLE `debit`
  ADD CONSTRAINT `debit_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
