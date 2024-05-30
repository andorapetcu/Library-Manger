USE [Library ]
GO
/****** Object:  StoredProcedure [dbo].[spBookSelectAllActive]    Script Date: 1/8/2024 7:22:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author: <Author,,Name>
-- Create date: <Create Date,,>
-- Description: <Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[spGenreSelectAllActive]
-- Add the parameters for the stored procedure here
AS
BEGIN
-- SET NOCOUNT ON added to prevent extra result sets from
-- interfering with SELECT statements.
SET NOCOUNT ON;
 
    -- Insert statements for procedure here
SELECT 
        [GenreId]
      ,[Name]
      , [Active]
  FROM [Library ].[dbo].[Genre]
  WHERE [Active] = 1;
END