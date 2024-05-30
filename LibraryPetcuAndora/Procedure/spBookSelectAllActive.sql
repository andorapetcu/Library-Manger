USE [Library ]
GO
/****** Object:  StoredProcedure [dbo].[spBookSelectAllActive]    Script Date: 1/8/2024 5:22:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author: <Author,,Name>
-- Create date: <Create Date,,>
-- Description: <Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[spBookSelectAllActive]
-- Add the parameters for the stored procedure here
AS
BEGIN
-- SET NOCOUNT ON added to prevent extra result sets from
-- interfering with SELECT statements.
SET NOCOUNT ON;
 
    -- Insert statements for procedure here
SELECT 
        [BookId]
      ,[Title]
      ,[AuthorId]
      ,[Publisher]
      ,[GenreId]
      ,[PublicationYear]
      , [Active]
  FROM [Library].[dbo].[Book]
  WHERE [Active] = 1;
END