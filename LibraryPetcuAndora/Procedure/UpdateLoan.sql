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
CREATE PROCEDURE UpdateLoan
    @LoanId int,
    @BookId int,
    @ReaderId int,
    @LoanDate date,
    @ReturnDate date
AS
BEGIN
    UPDATE Loan 
    SET BookId = @BookId, ReaderId = @ReaderId, LoanDate = @LoanDate, ReturnDate = @ReturnDate, Active = 1 
    WHERE LoanId = @LoanId
END

GO
