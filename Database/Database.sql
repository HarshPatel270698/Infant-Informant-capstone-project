-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 07, 2019 at 11:54 AM
-- Server version: 10.3.12-MariaDB
-- PHP Version: 7.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id8198950_ii`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `A_Id` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `Status` tinyint(1) NOT NULL,
  `S_Id` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `E_Id` int(4) NOT NULL,
  `E_Title` varchar(20) NOT NULL,
  `E_Date` date NOT NULL,
  `E_Details` varchar(250) NOT NULL,
  `E_Time` time NOT NULL,
  `Image1` varchar(150) NOT NULL,
  `Image2` varchar(150) NOT NULL,
  `Image3` varchar(150) NOT NULL,
  `fid` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`E_Id`, `E_Title`, `E_Date`, `E_Details`, `E_Time`, `Image1`, `Image2`, `Image3`, `fid`) VALUES
(1, 'New Year 2019', '2019-01-01', 'it\'s all about new year which we celebrate........................................................', '10:30:00', 'ev1.jpeg', 'ev2.jpeg', 'ev3.jpeg', 1),
(4, 'Paint Event for KG 1', '2019-01-08', 'Mandatory for all to participate in this event.', '10:30:00', '', '', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `F_Id` int(3) NOT NULL,
  `F_First_Name` varchar(10) NOT NULL,
  `F_Middle_Name` varchar(10) NOT NULL,
  `F_Last_Name` varchar(20) NOT NULL,
  `F_Gender` varchar(6) NOT NULL,
  `F_Contact_Number` bigint(12) NOT NULL,
  `F_Email` varchar(25) NOT NULL,
  `F_Joining_DATE` date NOT NULL,
  `F_DATE_Of_Birth` date NOT NULL,
  `F_Address` varchar(150) NOT NULL,
  `F_Password` varchar(25) NOT NULL,
  `isActive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`F_Id`, `F_First_Name`, `F_Middle_Name`, `F_Last_Name`, `F_Gender`, `F_Contact_Number`, `F_Email`, `F_Joining_DATE`, `F_DATE_Of_Birth`, `F_Address`, `F_Password`, `isActive`) VALUES
(1, 'Harsh', 'R', 'Patel', 'Female', 7096111590, 'harsh@gmail.com', '2018-07-09', '2018-06-27', 'A-101 Ganesh Opera,Ahmedabad', 'abc123', 1),
(2, 'Patel', 'Viral', 'Rameshbhai', 'Male', 859678956, 'viral@gmail.com', '2018-12-05', '1993-09-25', 'A101, Home villa, Ahmedabad', 'patelviral', 1);

-- --------------------------------------------------------

--
-- Table structure for table `fees`
--

CREATE TABLE `fees` (
  `fid` int(3) NOT NULL,
  `famount` decimal(5,0) NOT NULL,
  `fdate` date NOT NULL,
  `foption` varchar(30) NOT NULL,
  `T_no` varchar(20) NOT NULL,
  `S_GR_Number` varchar(10) NOT NULL,
  `std` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fees`
--

INSERT INTO `fees` (`fid`, `famount`, `fdate`, `foption`, `T_no`, `S_GR_Number`, `std`) VALUES
(1, 2500, '2019-01-03', 'Cheque', '15620215262', '180101', 'KG.1'),
(2, 200, '2019-01-02', 'debit', '1565fdsf', '180102', 'KG.2'),
(3, 6000, '2019-01-01', 'Debit', '5152156262', '180101', 'KG.1');

-- --------------------------------------------------------

--
-- Table structure for table `homework`
--

CREATE TABLE `homework` (
  `H_Id` int(3) NOT NULL,
  `H_Date` date NOT NULL,
  `H_Time` time NOT NULL,
  `H_Description` varchar(250) NOT NULL,
  `Standard` varchar(10) NOT NULL,
  `F_Id` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `homework`
--

INSERT INTO `homework` (`H_Id`, `H_Date`, `H_Time`, `H_Description`, `Standard`, `F_Id`) VALUES
(1, '2018-12-28', '10:36:00', 'ABCD 2 Times\r\nWrite numbers from 1 to 10 two times.', 'KG.1', 1),
(9, '2018-12-28', '16:36:55', 'ABCD 2 Times\r\nWrite numbers from 1 to 10 two times..', 'KG.2', 2),
(10, '2018-12-28', '16:42:59', 'Write numbers from 1 to 10 two times..', 'KG.2', 2),
(11, '2019-01-02', '15:23:40', 'ABCD 2 Times', 'KG.1', 1),
(12, '2019-01-03', '16:26:28', '1 to 100 in maths', 'KG.2', 1),
(13, '2019-01-30', '11:40:37', 'abcgyfh', 'KG.1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `leave_detail`
--

CREATE TABLE `leave_detail` (
  `L_Id` int(5) NOT NULL,
  `S_GR_Number` int(8) NOT NULL,
  `L_Reason` varchar(150) NOT NULL,
  `L_start` date NOT NULL,
  `L_end` date NOT NULL,
  `L_Accepted_Or_Declined` tinyint(1) DEFAULT NULL,
  `Reason` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leave_detail`
--

INSERT INTO `leave_detail` (`L_Id`, `S_GR_Number`, `L_Reason`, `L_start`, `L_end`, `L_Accepted_Or_Declined`, `Reason`) VALUES
(1, 180101, 'Marriage function', '2019-01-11', '2019-01-12', -1, 'You already exceeded your leave limit'),
(3, 180101, '\ntest', '2019-01-12', '2019-01-18', 1, NULL),
(29, 180101, 'Marriage Function', '2019-01-25', '2019-01-26', 0, NULL),
(30, 180101, 'reason', '2019-01-31', '2019-02-02', -1, 'ok'),
(31, 180101, 'seak leave', '2019-02-07', '2019-02-08', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `parents`
--

CREATE TABLE `parents` (
  `P_id` int(3) NOT NULL,
  `P_First_Name` varchar(20) NOT NULL,
  `P_Middle_Name` varchar(20) NOT NULL,
  `P_Last_Name` varchar(20) NOT NULL,
  `P_Gender` varchar(7) NOT NULL,
  `P_Contact_Number` bigint(12) NOT NULL,
  `P_Email` varchar(25) NOT NULL,
  `P_Address` varchar(150) NOT NULL,
  `S_GR_Number` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parents`
--

INSERT INTO `parents` (`P_id`, `P_First_Name`, `P_Middle_Name`, `P_Last_Name`, `P_Gender`, `P_Contact_Number`, `P_Email`, `P_Address`, `S_GR_Number`) VALUES
(1, 'first name', 'middle name', 'last naem', 'male', 7096111590, 'hp281268@gmail.com', 'address is written here', 0),
(2, 'first name', 'middle name', 'last naem', 'male', 7096111590, 'hp281268@gmail.com', 'address is written here', 0);

-- --------------------------------------------------------

--
-- Table structure for table `query`
--

CREATE TABLE `query` (
  `Q_Id` int(4) NOT NULL,
  `Q_Description` varchar(250) NOT NULL,
  `Q_Date` date NOT NULL,
  `Q_Time` time NOT NULL,
  `Q_Response` varchar(250) NOT NULL,
  `Q_Response_Date` datetime NOT NULL,
  `P_id` int(3) NOT NULL,
  `F_Id` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `result_detail`
--

CREATE TABLE `result_detail` (
  `R_Id` int(4) NOT NULL,
  `S_GR_Number` varchar(10) DEFAULT NULL,
  `Exam` varchar(15) DEFAULT NULL,
  `Maths` varchar(7) DEFAULT NULL,
  `Maths_Grade` varchar(2) DEFAULT NULL,
  `Gujarati` varchar(7) DEFAULT NULL,
  `Gujarati_Grade` varchar(2) DEFAULT NULL,
  `Total` varchar(9) DEFAULT NULL,
  `Total_Grade` varchar(2) NOT NULL,
  `Rank` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `result_detail`
--

INSERT INTO `result_detail` (`R_Id`, `S_GR_Number`, `Exam`, `Maths`, `Maths_Grade`, `Gujarati`, `Gujarati_Grade`, `Total`, `Total_Grade`, `Rank`) VALUES
(1, '180101', 'MID-1', '100/100', 'AA', '100/100', 'AA', '200/200', 'AA', '1/35'),
(2, '180102', 'MID-1', '95/100', 'AB', '96/100', 'AA', '191/200', 'AB', '5/35'),
(3, '180101', 'MID-2', '95/100', 'AA', '90/100', 'AB', '185/200', 'AB', '10/35'),
(4, '180102', 'MID-2', '56/100', 'CC', '89/100', 'AB', '145/200', 'BC', '7/35');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `S_GR_Number` varchar(10) NOT NULL,
  `S_First_Name` varchar(10) DEFAULT NULL,
  `S_Middle_Name` varchar(10) DEFAULT NULL,
  `S_Last_Name` varchar(20) DEFAULT NULL,
  `S_Gender` varchar(6) DEFAULT NULL,
  `S_Contact_Number` decimal(12,0) DEFAULT NULL,
  `S_Admission_Date` date DEFAULT NULL,
  `S_DATE_Of_Birth` date DEFAULT NULL,
  `S_Blood_Group` varchar(10) DEFAULT NULL,
  `S_Address` varchar(150) DEFAULT NULL,
  `Standard` varchar(20) NOT NULL,
  `F_Id` int(3) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `Image` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`S_GR_Number`, `S_First_Name`, `S_Middle_Name`, `S_Last_Name`, `S_Gender`, `S_Contact_Number`, `S_Admission_Date`, `S_DATE_Of_Birth`, `S_Blood_Group`, `S_Address`, `Standard`, `F_Id`, `password`, `Image`) VALUES
('180101', 'Patel', 'Harsh', 'Rajeshkumar', 'Male', 123456789, '2018-10-19', '2017-11-22', 'A+', 'A-503 Megh Malhar Ahmedabad', 'KG.1', NULL, 'abc', 'profile1.jpg'),
('180102', 'Patel', 'Harsh', 'Rajeshkumar', 'Male', 7096111590, '2016-09-08', '1998-06-27', 'A+', 'A101,Ganeshopera,opp. Sadguruvatika', 'KG.2', NULL, 'def', 'profile1.jpg'),
('180103', 'Lakhani', 'Harshal', 'Gulabbhai', 'Male', 8956789457, '2019-02-01', '2018-10-17', 'A+', 'Sadguru vatik', 'KG.1', 1, 'HelloHarshal', '');

-- --------------------------------------------------------

--
-- Table structure for table `timetable`
--

CREATE TABLE `timetable` (
  `T_Id` int(2) NOT NULL,
  `Standard` varchar(10) NOT NULL,
  `Month` int(2) NOT NULL,
  `Year` year(4) NOT NULL,
  `IMAGE_name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timetable`
--

INSERT INTO `timetable` (`T_Id`, `Standard`, `Month`, `Year`, `IMAGE_name`) VALUES
(1, 'KG.2', 1, 2018, 'timetable.jpg'),
(2, 'KG.1', 2, 2019, 'timetable1.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`A_Id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`E_Id`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`F_Id`);

--
-- Indexes for table `fees`
--
ALTER TABLE `fees`
  ADD PRIMARY KEY (`fid`);

--
-- Indexes for table `homework`
--
ALTER TABLE `homework`
  ADD PRIMARY KEY (`H_Id`);

--
-- Indexes for table `leave_detail`
--
ALTER TABLE `leave_detail`
  ADD PRIMARY KEY (`L_Id`);

--
-- Indexes for table `parents`
--
ALTER TABLE `parents`
  ADD PRIMARY KEY (`P_id`);

--
-- Indexes for table `query`
--
ALTER TABLE `query`
  ADD PRIMARY KEY (`Q_Id`);

--
-- Indexes for table `result_detail`
--
ALTER TABLE `result_detail`
  ADD PRIMARY KEY (`R_Id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`S_GR_Number`);

--
-- Indexes for table `timetable`
--
ALTER TABLE `timetable`
  ADD PRIMARY KEY (`T_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `A_Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `E_Id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `faculty`
--
ALTER TABLE `faculty`
  MODIFY `F_Id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `fees`
--
ALTER TABLE `fees`
  MODIFY `fid` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `homework`
--
ALTER TABLE `homework`
  MODIFY `H_Id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `leave_detail`
--
ALTER TABLE `leave_detail`
  MODIFY `L_Id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `parents`
--
ALTER TABLE `parents`
  MODIFY `P_id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `result_detail`
--
ALTER TABLE `result_detail`
  MODIFY `R_Id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `timetable`
--
ALTER TABLE `timetable`
  MODIFY `T_Id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
