-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2024 at 06:52 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `finance`
--

-- --------------------------------------------------------

--
-- Table structure for table `balance`
--

CREATE TABLE `balance` (
  `user_id` varchar(50) NOT NULL,
  `balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `balance`
--

INSERT INTO `balance` (`user_id`, `balance`) VALUES
('nijo1439', 65001),
('hetr1391', 45500),
('tave8350', 200000),
('sapa9635', 45000),
('sapa5421', 50000),
('nijo7039', 20000);

-- --------------------------------------------------------

--
-- Table structure for table `budgets`
--

CREATE TABLE `budgets` (
  `user_id` varchar(50) NOT NULL,
  `budget_name` varchar(50) NOT NULL,
  `period` varchar(50) NOT NULL,
  `amount` double NOT NULL,
  `category` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `budgets`
--

INSERT INTO `budgets` (`user_id`, `budget_name`, `period`, `amount`, `category`) VALUES
('nijo1439', 'Monthly Grocery budget', 'Monthly', 5000, 'Groceries');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `user_id` varchar(50) NOT NULL,
  `transaction_id` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `day` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`user_id`, `transaction_id`, `type`, `category`, `description`, `day`, `month`, `year`, `amount`) VALUES
('nijo1439', 'nijo1439_2', 'Expense', 'Food', 'Groceries', 3, 8, 2023, 1000),
('nijo1439', 'nijo1439_3', 'Income', 'Salary', 'Pay', 1, 9, 2023, 100000),
('hetr1391', 'hetr1391_4', 'Expense', 'Education', 'tution fees', 30, 9, 2023, 2500),
('hetr1391', 'hetr1391_5', 'Expense', 'Communication', 'electricity_bill', 1, 9, 2023, 1200),
('hetr1391', 'hetr1391_6', 'Expense', 'Shopping', 'clothes', 15, 4, 2023, 800),
('hetr1391', 'hetr1391_7', 'Income', 'Freelance', 'project payment', 4, 9, 2023, 5000),
('nijo1439', 'nijo1439_8', 'Expense', 'Transportation', 'fuel refill', 10, 10, 2023, 2000),
('nijo1439', 'nijo1439_9', 'Expense', 'Entertainment', 'movie tickets', 15, 10, 2023, 3500),
('nijo1439', 'nijo1439_10', 'Expense', 'Health', 'Doctor\'s appointment', 3, 10, 2023, 1000),
('nijo1439', 'nijo1439_11', 'Expense', 'Transportation', 'flight booking', 2, 10, 2023, 3200),
('nijo1439', 'nijo1439_12', 'Expense', 'Investments', 'Stock purchase', 3, 10, 2023, 3000),
('nijo1439', 'nijo1439_13', 'Income', 'Sale', 'garage sale proceed', 1, 11, 2023, 5000),
('nijo1439', 'nijo1439_14', 'Income', 'lending,renting', 'rent received', 1, 10, 2023, 8000),
('nijo1439', 'nijo1439_16', 'Income', 'Freelance', 'project work', 5, 10, 2023, 7898),
('nijo1439', 'nijo1439_17', 'expense', 'Sale', '---', 1, 10, 2023, 2000),
('nijo1439', 'nijo1439_19', 'Income', 'Salary', '-', 1, 10, 2023, 45000),
('nijo1439', 'nijo1439_20', 'Expense', 'Food', '--', 2, 1, 2023, 2),
('sapa5421', 'sapa5421_22', 'Income', 'Salary', '-', 2, 3, 2023, 45000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` varchar(50) NOT NULL,
  `f_name` varchar(50) NOT NULL,
  `l_name` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile_no` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `f_name`, `l_name`, `age`, `email`, `mobile_no`, `password`) VALUES
('nijo1439', 'nishita', 'joshi', 19, 'joshinishita01@gmail.com', '9104188101', '@np2005@'),
('hetr1391', 'Heva', 'Trivedi', 19, 'hevatrivedi@gmail.com', '9428951977', '@heva123'),
('tave8350', 'tanvi', 'vejani', 19, 'tanu@gmaill.com', '7016739489', 'tanu@05.'),
('sapa9635', 'saloni', 'pancholi', 19, 'salu@gmail.com', '9104188191', '@saloni@'),
('sapa5421', 'saloni', 'pancholi', 19, 'josjfj@gmail.com', '9104188101', '@saloni@'),
('nijo7039', 'Nishita', 'JOshi', 19, 'joshinishita01@gmail.com', '9212398981', '@Nishu0!');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
