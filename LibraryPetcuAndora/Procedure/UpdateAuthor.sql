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
CREATE PROCEDURE UpdateAuthor
    @AuthorId int,
    @FirstName nvarchar(50),
    @LastName nvarchar(50),
    @Nationality nvarchar(50),
    @GenreId int
AS
BEGIN
    UPDATE Author
    SET FirstName = @FirstName, LastName = @LastName, Nationality = @Nationality, GenreId = @GenreId 
    WHERE AuthorId = @AuthorId
END

GO
