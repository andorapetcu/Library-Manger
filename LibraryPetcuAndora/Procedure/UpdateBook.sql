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
CREATE PROCEDURE UpdateBook
    @BookId int,
    @Title nvarchar(50),
    @AuthorId int,
    @Publisher nvarchar(50),
    @GenreId int,
    @PublicationYear int
AS
BEGIN
    UPDATE Book
    SET Title = @Title, AuthorId = @AuthorId, Publisher = @Publisher, GenreId = @GenreId, PublicationYear = @PublicationYear, Active = 1 
    WHERE BookId = @BookId
END

GO
