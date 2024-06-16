# Use an official Node.js runtime as a parent image
FROM node:14

# Install msmtp and required dependencies
RUN apt-get update && apt-get install -y msmtp msmtp-mta ca-certificates

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the package.json and package-lock.json files
COPY package*.json ./

# Install any needed packages specified in package.json
RUN npm install

# Copy the rest of the application code
COPY . .

# Copy the msmtprc file to the root of the container's filesystem
COPY msmtprc /root/.msmtprc

# Make the main.sh script executable
RUN chmod +x main.sh

# Make the msmtprc file readable
RUN chmod 600 /root/.msmtprc

# Expose the port the app runs on
EXPOSE 8069

# Define the command to run the app
CMD [ "node", "server.js" ]
