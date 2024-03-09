## AnimeBox API Documentation

Welcome to AnimeBox, your one-stop shop for exploring the vast world of anime! This API empowers you to not only manage your anime catalog but also discover hidden gems based on your preferences using the power of AI.

### Getting Started

This API is built with cutting-edge technologies (don't worry, they're all here to make your experience smoother!), but getting started is a breeze. Here's what you'll need:

* **Java / Spring Boot:** We use this dynamic duo for the backend development, ensuring a robust and efficient foundation.
* **Python / Flask:** Our Python buddy Flask crafts a lightweight and flexible API service on the client-side.
* **MySQL Database:** This trusty database stores all your precious anime information and watch lists.
* **A.I. Power (gemini_ai):** Leveraging Google's gemini_ai, we generate personalized anime recommendations based on your desires.
* **And More!** Other cool tech like Eureka Server, Feign Client, and Postman contribute to the magic behind the scenes.

### Unleash Your Inner Otaku

This API offers a plethora of functionalities to manage your anime world and unearth new favorites:

**1. Build Your Anime Catalog:**

* **Create new anime entries:** Breathe life into your collection by adding new anime with details like titles, summaries, categories, and more.
* **Manage your collection:** Edit existing entries, update details, or remove anime you've completed from your list. 
* **Explore all the anime:** Dive into the full catalog and discover a universe of anime waiting to be explored.

**2. Discover Your Next Anime Obsession:**

* **AI-powered recommendations:** Feeling lost in the anime sea? Tell us your preferences, and our AI engine will curate a personalized list of recommendations just for you!

**3. Keep Track of Your Watch List:**

* **Add anime to your watch list:** Never forget what's next on your anime journey by adding titles to your watch list for easy access.
* **View your watch list:** Keep track of your progress and see what exciting adventures await you.

**Getting Started:**

1. **Clone the AnimeBox Repository:** Grab the code from GitHub to embark on your anime management journey! 
2. **Ensure Essential Tools:** Make sure you have Java, Python, and any required dependencies installed to run the API. 
3. **Configure Database Connection:** Set up database connection details in the corresponding application properties files. 
4. **Run the System:** Start Eureka Server, Cloudgateway, and the necessary microservices to bring AnimeBox to life! 
5. **Explore and Interact:** Use Postman or other API testing tools to interact with the available endpoints. ️

**Anime JSON Request Examples:**

**1. Adding a New Anime Entry:**

```json
{
  "animeName": "Your Anime Name Here",
  "history": "A brief summary of the anime's plot.",
  "category": "l",
  "subject": "The main theme(s) of the anime.",
  "numberOfEpisodes": 12,  
  "imdb": 8.5          
}
``` 


**2. User Input for Anime Recommendations:**

```json
{
  "user_input": "I like friendship, superpowers, and fun anime."
}
``` 

### Dive Deeper: API Endpoints

For those who like to get technical, here's a breakdown of the API endpoints available:

**Anime Catalog API (Java):**

| Endpoint          | Description                                                                       |
|-------------------|-----------------------------------------------------------------------------------|
| `/anime` (POST)    | Create a new anime entry.                                                        |
| `/anime/{id}` (GET) | Retrieve an anime entry by its ID.                                              |
| `/anime` (GET)      | Retrieve all anime entries.                                                     |
| `/anime/category` (GET) | Retrieve all anime entries in a specific category (provide category as a query parameter). |
| `/anime/{id}` (PUT) | Update an existing anime entry.                                                                |
| `/anime/{id}` (DELETE) | Delete an anime entry by its ID.                                                            |

**Watch List API (Java):**

| Endpoint          | Description                                                                       |
|-------------------|-----------------------------------------------------------------------------------|
| `/watch-list/{id}` (POST) | Add an anime to the user's watch list by providing the anime's ID.        |
| `/watch-list` (GET)    | Retrieve the user's watch list.                                              |
| `/watch-list/{id}` (DELETE) | Delete an anime from the user's watch list by providing its ID.         |

**Anime Finder API (Java):**

| Endpoint          | Description                                                                                      |
|-------------------|-----------------------------------------------------------------------------------|
| `/api/anime-finder-bot` (POST) | Provide user input for anime recommendations and receive a list of recommendations. |

**Gemini API (Python):**

| Endpoint          | Description                                                                       |
|-------------------|-----------------------------------------------------------------------------------|
| `/get_response` (POST) | Receives a message from the AnimeFinder API and interacts with gemini_ai to generate recommendations. |

This comprehensive documentation equips you to navigate the exciting world of AnimeBox! Dive in, explore, and discover your next anime obsession!

# Future Work - Step-by-Step Plan

## 1. Expand Anime Database
   - Add a diverse range of animes to the database to enhance the catalog.

## 2. Implement Spring Security
   - Enhance API security by integrating Spring Security.
   - Implement user authentication and authorization for secure access.

## 3. Integrate Circuit Breaker
   - Implement a circuit breaker pattern (e.g., Netflix Hystrix) to improve system resilience.
   - Handle and recover from potential service failures gracefully.

## 4. Develop React.js Website
   - Create a user-friendly website using React.js to interact with the AnimeBox API.
   - Include features for browsing animes, managing watchlists, and receiving recommendations.

## 5. Dockerize the Project
   - Containerize the entire project using Docker for improved portability.
   - Simplify deployment and environment setup with containerization.

## 6. Transition to Kubernetes
   - Migrate from Eureka server to Kubernetes for enhanced orchestration and scalability.
   - Leverage Kubernetes features for efficient container management.

Feel free to prioritize and modify the plan based on your project's needs and objectives. Happy coding! 🚀

