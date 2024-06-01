# Use an official Node.js runtime as a parent image
FROM node:14

# Install curl
RUN apt-get update && apt-get install -y curl

# Set the working directory
WORKDIR /usr/src/app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of your application code and other files
COPY . .

# Expose the port your app runs on
EXPOSE 8069

# Define the command to run your app
CMD ["node", "server.js"]
