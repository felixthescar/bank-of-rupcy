# Use the Node.js image from Docker Hub
FROM node:16

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Expose the port the app runs on
EXPOSE 5173

# Run the app
CMD ["npm", "run", "dev"]
