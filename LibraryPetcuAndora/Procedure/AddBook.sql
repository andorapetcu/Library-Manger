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
CREATE PROCEDURE AddBook
    @Title nvarchar(50),
    @AuthorId int,
    @Publisher nvarchar(50),
    @GenreId int,
    @PublicationYear int
AS
BEGIN
    INSERT INTO Book (Title, AuthorId, Publisher, GenreId, PublicationYear, Active)
    VALUES (@Title, @AuthorId, @Publisher, @GenreId, @PublicationYear, 1)
END

GO
