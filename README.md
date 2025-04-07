# Remote UI Loader

This project is a simple Java application that loads a user interface (UI) from a JSON file hosted at a specified URL. The UI is rendered using Java Swing components.

## How to Use

1. **Compile the Project**: Ensure you have Java installed on your system. Compile the project using your preferred Java IDE or command line.

2. **Run the Application**: Execute the `RemoteUILoader` class

3. **Provide the URL**: Enter the URL of the JSON file when prompted. The application will fetch the JSON, parse it, and render the UI accordingly.

## JSON Format

The JSON file should have the following structure:

```json
{
  "components": [
    {
      "type": "label",
      "text": "Your label text here"
    },
    {
      "type": "button",
      "text": "Your button text here"
    }
  ]
}
```

- **components**: An array of UI components, can have arbitrary size and order of components.
  - **type**: The type of the component. Currently supported types are `label` and `button`.
  - **text**: The text to display on the component.

## Example

Here is an example of a JSON file that defines a simple UI with a label and a button:

```json
{
  "components": [
    {
      "type": "label",
      "text": "Welcome to the Remote UI"
    },
    {
      "type": "button",
      "text": "Click Me"
    }
  ]
}
```

## Dependencies

- Java Development Kit (JDK)
- Gson library for JSON parsing
- Maven

## Notes

- Ensure the JSON file is accessible via the provided URL.
- The application currently supports basic UI components like labels and buttons. More components can be added by extending the JSON format and the application logic. 