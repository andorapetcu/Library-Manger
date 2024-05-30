-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE UpdateReader
    @ReaderId int,
    @FirstName nvarchar(50),
    @LastName nvarchar(50),
    @CNP nvarchar(50),
    @Phone nvarchar(50),
    @Email nvarchar(50)
AS
BEGIN
    UPDATE Reader 
    SET FirstName = @FirstName, LastName = @LastName, CNP = @CNP, Phone = @Phone, Email = @Email, Active = 1 
    WHERE ReaderId = @ReaderId
END

GO
