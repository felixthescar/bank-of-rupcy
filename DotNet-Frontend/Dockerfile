# Use the official .NET SDK image to build the application
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /app

# Copy the solution file and project files
COPY TragInElDotNet.sln .
COPY TragInElDotNet/TragInElDotNet.csproj ./TragInElDotNet/

# Restore dependencies
WORKDIR /app/TragInElDotNet
#RUN dotnet restore

# Copy everything else and build
COPY TragInElDotNet/. .
RUN dotnet publish -c Release -o out

# Use the official .NET runtime image to run the application
FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS runtime
WORKDIR /app
COPY --from=build /app/TragInElDotNet/out ./
ENV ASPNETCORE_ENVIRONMENT=Development
ENTRYPOINT ["dotnet", "TragInElDotNet.dll"]
