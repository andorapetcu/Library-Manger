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
CREATE PROCEDURE AddAuthor
    @FirstName nvarchar(50),
    @LastName nvarchar(50),
    @Nationality nvarchar(50),
    @GenreId int
AS
BEGIN
    INSERT INTO [Library ].[dbo].[Author] (FirstName, LastName, Nationality, GenreId, Active)
    VALUES (@FirstName, @LastName, @Nationality, @GenreId, 1)
END
GO
