-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 13, 2022 at 09:25 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parcialpracticodds`
--
CREATE DATABASE IF NOT EXISTS `parcialpracticodds` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `parcialpracticodds`;

-- --------------------------------------------------------

--
-- Table structure for table `app_roles`
--

CREATE TABLE `app_roles` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `app_users`
--

CREATE TABLE `app_users` (
  `id` bigint(20) NOT NULL,
  `altura` double DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `comentarios`
--

CREATE TABLE `comentarios` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_publicacion` datetime DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `comentario_padre` bigint(20) DEFAULT NULL,
  `medicamento` bigint(20) NOT NULL,
  `usuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `laboratorios`
--

CREATE TABLE `laboratorios` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `medicamentos`
--

CREATE TABLE `medicamentos` (
  `med_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `laboratorio` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `medicamentos_por_combo`
--

CREATE TABLE `medicamentos_por_combo` (
  `combo_medicamento` bigint(20) NOT NULL,
  `medicamento` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roles_por_usuario`
--

CREATE TABLE `roles_por_usuario` (
  `usuario` bigint(20) NOT NULL,
  `rol` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `app_roles`
--
ALTER TABLE `app_roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `app_users`
--
ALTER TABLE `app_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Comentarios_ComentarioPadre` (`comentario_padre`),
  ADD KEY `FK_Comentarios_Medicamento` (`medicamento`),
  ADD KEY `FK_Comentarios_Usuario` (`usuario`);

--
-- Indexes for table `laboratorios`
--
ALTER TABLE `laboratorios`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `medicamentos`
--
ALTER TABLE `medicamentos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Medicamentos_Laboratorio` (`laboratorio`);

--
-- Indexes for table `medicamentos_por_combo`
--
ALTER TABLE `medicamentos_por_combo`
  ADD KEY `FK_MedicamentosPorCombo_Medicamento` (`medicamento`),
  ADD KEY `FK_MedicamentosPorCombo_ComboMedicamento` (`combo_medicamento`);

--
-- Indexes for table `roles_por_usuario`
--
ALTER TABLE `roles_por_usuario`
  ADD KEY `FK_RolesPorUsuario_Rol` (`rol`),
  ADD KEY `FK_RolesPorUsuario_Usuario` (`usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `app_roles`
--
ALTER TABLE `app_roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `app_users`
--
ALTER TABLE `app_users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `laboratorios`
--
ALTER TABLE `laboratorios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `medicamentos`
--
ALTER TABLE `medicamentos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `FK_Comentarios_ComentarioPadre` FOREIGN KEY (`comentario_padre`) REFERENCES `comentarios` (`id`),
  ADD CONSTRAINT `FK_Comentarios_Medicamento` FOREIGN KEY (`medicamento`) REFERENCES `medicamentos` (`id`),
  ADD CONSTRAINT `FK_Comentarios_Usuario` FOREIGN KEY (`usuario`) REFERENCES `app_users` (`id`);

--
-- Constraints for table `medicamentos`
--
ALTER TABLE `medicamentos`
  ADD CONSTRAINT `FK_Medicamentos_Laboratorio` FOREIGN KEY (`laboratorio`) REFERENCES `laboratorios` (`id`);

--
-- Constraints for table `medicamentos_por_combo`
--
ALTER TABLE `medicamentos_por_combo`
  ADD CONSTRAINT `FK_MedicamentosPorCombo_ComboMedicamento` FOREIGN KEY (`combo_medicamento`) REFERENCES `medicamentos` (`id`),
  ADD CONSTRAINT `FK_MedicamentosPorCombo_Medicamento` FOREIGN KEY (`medicamento`) REFERENCES `medicamentos` (`id`);

--
-- Constraints for table `roles_por_usuario`
--
ALTER TABLE `roles_por_usuario`
  ADD CONSTRAINT `FK_RolesPorUsuario_Rol` FOREIGN KEY (`rol`) REFERENCES `app_roles` (`id`),
  ADD CONSTRAINT `FK_RolesPorUsuario_Usuario` FOREIGN KEY (`usuario`) REFERENCES `app_users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
