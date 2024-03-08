from flask import Flask, request, jsonify
import google.generativeai as genai

# Error handling (consider proper exception handling for production)
def handle_error(message):
    return jsonify({"error": message}), 500

# Configure Gemini model (replace with your API key)
def configure_gemini():
    try:
        api_key = "your api key"  # Replace with your actual API key
        genai.configure(api_key=api_key)

        generation_config = {
            "temperature": 0.9,
            "top_p": 1,
            "top_k": 1,
            "max_output_tokens": 2048,
        }

        safety_settings = [
            {
                "category": "HARM_CATEGORY_HARASSMENT",
                "threshold": "BLOCK_MEDIUM_AND_ABOVE"
            },
            {
                "category": "HARM_CATEGORY_HATE_SPEECH",
                "threshold": "BLOCK_MEDIUM_AND_ABOVE"
            },
            {
                "category": "HARM_CATEGORY_SEXUALLY_EXPLICIT",
                "threshold": "BLOCK_MEDIUM_AND_ABOVE"
            },
            {
                "category": "HARM_CATEGORY_DANGEROUS_CONTENT",
                "threshold": "BLOCK_MEDIUM_AND_ABOVE"
            },
        ]

        model = genai.GenerativeModel(model_name="gemini-1.0-pro",
                                      generation_config=generation_config,
                                      safety_settings=safety_settings)
        return model
    except Exception as e:
        return handle_error(f"Error configuring Gemini model: {str(e)}")

# Global variable to store the model instance (consider thread safety)
global_model = configure_gemini()  # Initialize model on application startup

app = Flask(__name__)

@app.route("/get_response", methods=["POST"])
def get_response():
    # Validate request data
    if not request.is_json:
        return handle_error("Request must be in JSON format")
    user_input = request.json.get("user_input")
    if not user_input:
        return handle_error("Missing 'user_input' field in request body")

    # Ensure model is configured
    if not global_model:
        return handle_error("Gemini model could not be configured")

    try:
        # Interact with Gemini model
        convo = global_model.start_chat(history=[])
        convo.send_message(user_input)
        response = convo.last.text
        
        anime_list = response.strip('[]')
        return anime_list
    except Exception as e:
        return handle_error(f"Error during Gemini interaction: {str(e)}")

if __name__ == "__main__":
    app.run(debug=True)
