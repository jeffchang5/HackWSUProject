module.exports = {
    "extends": ["airbnb", "plugin:flowtype/recommended"],
    "parser": "babel-eslint",
    "plugins": [
        "react",
        "jsx-a11y",
        "import",
        "flowtype",
    ],
    "rules": {
      "import/extensions": 0,
      "import/no-unresolved": 0,
      "import/no-extraneous-dependencies": 0,
    },
    "env": {
      "browser": true,
      "jasmine": true,
    },
};