-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 22, 2024 at 09:12 PM
-- Server version: 5.5.20
-- PHP Version: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `electro`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `product_id`, `name`, `address`, `phone_number`, `email`, `price`, `quantity`, `total_price`) VALUES
(8, 1, 'salah', '112 rachidia', '+21266666', 'ana@gmail.com', '3100.00', 1, '3100.00'),
(9, 18, 'anass', '33 meknes', '+212212121', 'anass@gmail.com', '600.00', 2, '1200.00');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `buy_price` decimal(10,2) NOT NULL,
  `sell_price` decimal(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `name`, `category`, `buy_price`, `sell_price`, `quantity`, `image_path`) VALUES
(1, 'SAMSUNG A25 6G/128G', 'tel', '3100.00', '3249.00', 10, 'C:\\Users\\John\\Desktop\\Products_imgs\\sams1.jpg'),
(2, 'SAMSUNG A15 8G/256G', 'tel', '2400.00', '2490.00', 10, 'C:\\Users\\John\\Desktop\\Products_imgs\\sams2.jpg'),
(3, 'SAMSUNG GALAXY S24 ULTRA 256 GO', 'tel', '16000.00', '16990.00', 2, 'C:\\Users\\John\\Desktop\\Products_imgs\\sams3.jpg'),
(4, 'HUAWEI NOVA 10SE 256GO SILVER', 'tel', '2700.00', '2799.00', 5, 'C:\\Users\\John\\Desktop\\Products_imgs\\huawei1.jpg'),
(5, 'HUAWEI SMARTPHONE NOVA Y60 GREEN', 'tel', '1150.00', '1240.00', 10, 'C:\\Users\\John\\Desktop\\Products_imgs\\huawei2.jpg'),
(6, 'HONOR X9B 5G 12G/256GB', 'tel', '3800.00', '3999.00', 5, 'C:\\Users\\John\\Desktop\\Products_imgs\\honor1.jpg'),
(7, 'SAMSUNG NEO QLED QA65QN90BAUXMV 4K', 'tv', '19000.00', '20000.00', 2, 'C:\\Users\\John\\Desktop\\Products_imgs\\tv1.jpg'),
(8, 'APPLE IPHONE 15 PLUS 128GB', 'tel', '15000.00', '15990.00', 5, 'C:\\Users\\John\\Desktop\\Products_imgs\\iph1.jpg'),
(9, 'PHONEIX IPHONE 8 PLUS 64 GB GOLD', 'tel', '1800.00', '1999.00', 10, 'C:\\Users\\John\\Desktop\\Products_imgs\\iph2.jpg'),
(10, 'APPLE IPHONE 7 32GB', 'tel', '1850.00', '1999.00', 5, 'C:\\Users\\John\\Desktop\\Products_imgs\\iph3.jpg'),
(11, 'LG 65NANO776RA ', 'tv', '10000.00', '10299.00', 1, 'C:\\Users\\John\\Desktop\\Products_imgs\\tv2.jpg'),
(12, 'SKYWORTH QLED 65SUE9500', 'tv', '7000.00', '7999.00', 2, 'C:\\Users\\John\\Desktop\\Products_imgs\\tv3.jpg'),
(13, 'ASUS N7400 I5 SILVER', 'ord', '13000.00', '13999.00', 3, 'C:\\Users\\John\\Desktop\\Products_imgs\\ord1.jpg'),
(14, 'APPLE MACBOOK AIR MLY23FN/A M2 512GB', 'ord', '11700.00', '11799.00', 5, 'C:\\Users\\John\\Desktop\\Products_imgs\\ord2.jpg'),
(15, 'LENOVO IP5 R5 4500U 8G 512G W10 15,6¨GREY', 'ord', '5000.00', '5999.00', 10, 'C:\\Users\\John\\Desktop\\Products_imgs\\ord3.jpg'),
(16, 'HP 6L9K4', 'ord', '6000.00', '6999.00', 4, 'C:\\Users\\John\\Desktop\\Products_imgs\\ord4.jpg'),
(17, 'MOULINEX POWELIX DPA141', 'electro', '600.00', '699.00', 2, 'C:\\Users\\John\\Desktop\\Products_imgs\\electro1.jpg'),
(18, 'KRUPS GRILLE PAIN EXCELLENCE 2 FENTES', 'electro', '600.00', '649.00', 1, 'C:\\Users\\John\\Desktop\\Products_imgs\\electro2.jpg'),
(19, 'DELONGHI ECP33.21', 'electro', '2200.00', '2399.00', 5, 'C:\\Users\\John\\Desktop\\Products_imgs\\electro3.jpg'),
(20, 'Cuisinière 5 feux cache-bouteille', 'electro', '9000.00', '9290.00', 1, 'C:\\Users\\John\\Desktop\\Products_imgs\\electro4.jpg');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
