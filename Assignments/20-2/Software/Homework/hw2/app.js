// TODO: edit this file

// This is a list where your records should be stored. See `parseAndSave`.
let records = [];

// `parseAndSave(text)` is a function called with one argument `text`, the content of the babyname CSV file.
// It is invoked only once at the start of application.
// TODO: parse the csv text and save data records into the global variable `records` properly,
// so that the other functions use them with ease. After calling this function, `records` should
// contain the parsed data of every year like below.
// csv_line = {year,rank,name,gender,rank_delta}
//     e.g. records: [{year: 2001, rank: 1, name: "Jacob", gender: "M", rankChange: null},
//                    {year: 2001, rank: 2, name: "Michael", gender: "M", rankChange: null},
//                    ...]
// Note: a CSV text can end with trailing line-break character '\n'. Whether it exists or not, 
// the function should parse `text` correctly. Also, records should be stored in the same order
// in which they appear in a csv text. You can assume that at the first line is always a csv header.
function parseAndSave(text) {
    // TODO: Fill this function. (3 points)
    const linesInText = text.split(/\r\n|\n/);
    const fieldNames = linesInText[0].split(',');

    // Read lines in text, start from 1
    for (let i = 1; i < linesInText.length; i++) {
        const fields = linesInText[i].split(','); // line
        if (fields.length == 5) {  // except empty line
            // const record = new Map();  // line
            // for (let j = 0; j < fields.length; j++) {
            //     record.set(fieldNames[j], fields[j]);
            // }
            record = {
                year: fields[0],
                rank: fields[1],
                name: fields[2],
                gender: fields[3],
                rank_delta: fields[4]
            }
            records.push(record);
        }
    }
}


// `provideYearData(year)` is a function that receives a year and returns an array of data object corresponding to that year.
// Note that male and female record with the same rank should be joined together to form one object.
// TODO: return all data objects of a specific year, that are joined and organized by rank in an ascending order.
// The example of returned array is as follows.
//     e.g. [{rank: 1, male: "Jacob", maleRankChange: 0, female: "Isabella", femaleRankChange: 0},
//           {rank: 2, male: "Ethan", maleRankChange: 0, female: "Sophia", femaleRankChange: -2},    
//           ...,
//           {rank: 1000, male: "Keshawn", maleRankChange: 113, female: "Karley", femaleRankChange: 17}]
function provideYearData(year) {
    // TODO: Fill in this function. (5 points)

    // Filter records that only 'year' datas
    let returnRecords = [];
    const yearRecords = records.filter(it => it.year.includes(year));

    // Join two records that are having the same rank, M-F
    for (let i = 1; i <= 1000; i++) {
        // man and woman that have the same rank
        const rankRecords = yearRecords.filter(it => it.rank == i);
        const maleRecord = rankRecords[0];  // {2001,730,Judah,M,}
        const femaleRecord = rankRecords[1];  // {2001,730,Kacie,F,}

        const joinedRecord = {
            rank: maleRecord.year,
            male: maleRecord.name,
            maleRankChange: maleRecord.rank_delta,
            female: femaleRecord.name,
            femaleRankChange: femaleRecord.rank_delta
        }  // {rank: 1000, male: "Keshawn", maleRankChange: 113, female: "Karley", femaleRankChange: 17}

        returnRecords.push(joinedRecord);
    }

    return returnRecords;
}

// provideChartData(name, gender) is a function called when a user wants
// to see the chart showing the year-on-year change of rank of a specific name.
// TODO: return a list of all objects from 2001 to 2018 in the format of `{year: <year>, rank: <rank>}`
// of a specific name specified by the arguments, name and gender.
// If there are no records with the name and gender for some years,
// either you can set the values of the ranks to `undefined` or not return those records at all.
// The example of return data is as follow.
//     e.g. [{year: 2001, rank: undefined},
//           {year: 2002, rank: 613},
//           ...,
//           {year: 2018, rank: 380}]
// You can also return data excluding `undefined` value as below.
//     e.g. [{year: 2002, rank: 613},
//           ...,
//           {year: 2018, rank: 380}]
function provideChartData(name, gender) {
    // TODO: Fill in this function. (2 points)

    // Filter records that only matches 'name && gender' 
    // by {year: value, rank: value}
    let returnRecords = [];

    // One specific man/woman data (i.e., Jacob, M)
    const onemanRecords = records.filter(it => it.name == name && it.gender == gender);

    // Recap the record by the returning format
    for (let i = 0; i < onemanRecords.length; i++) {
        // Convert {year: '2001', rank: '1', name: 'Jacob', gender: 'M', rank_delta: ''} as {year: 2018, rank: 380}]

        const currRecord = {
            year: onemanRecords[i].year,
            rank: onemanRecords[i].rank
        }

        returnRecords.push(currRecord);
    }
    // // This is just a reference for the return value's format. Delete this and fill your own 
    // // proper code to return the correct data.
    // return [{year: 2001, rank: 3}, {year: 2002, rank: undefined}];
    return returnRecords;
}


// `handleSignUpFormSubmit(form)` is called when a user submits the sign up form.
// `form` is the target HTML form element (L82~ in index.html).
// TODO: validate the form. (5 points)
function handleSignUpFormSubmit(form) {
    // TODO: Fill in the rest of function to get the HTML form element as above.

    // Hint: you can use the `RegExp` class for matching a string.

    // The return data format is as follows. For the given `form` argument, you should
    // correctly process the validation, filling in `alertMessage`, and `validationResults`. 
    // When you deal with `validationResults`, the values of `message` should be set to `null`
    // for the valid input fields. (See the example below.)
    // Below is just a reference for the return value's format. Delete this and fill your own
    // proper code to return the correct data.

    // IMPORTANT NOTE: You must use the argument `form` rather than directly using APIs such as `document.getElementId` or `document.querySelector`.
    //                 Plus, please do not call `alert` function here.
    //                 For debugging purpose, you can use `console.log`.

    let alertMessage;
    let validationResults = [];
    // 'TODO: Fill in this alert message properly';

    // Checking the validity by using the RegExp
    const userFirst = form['first-name'].value;
    const userLast = form['last-name'].value;
    const userEmail = form['email'].value;
    const userBirth = form['date-of-birth'].value;

    const regexForName = /^[A-Za-z]+$/;
    const regexForEmail = /^[^@\s]+@{1}[^@.\s]+\.{1}[a-z]{2,3}$/;
    const regexForBirth = /^(19[0-9][0-9]|20[01][0-9]|2020)-(0[1-9]|10|11|12)-(0[1-9]|[12][0-9]|30|31)$/;


    const checkValidity = {
        firstName: regexForName.test(userFirst),
        lastName: regexForName.test(userLast),
        email: regexForEmail.test(userEmail),
        birth: regexForBirth.test(userBirth)
    }
    
    // Make concise!
    function isTrue(bool) {
        return bool;
    }

    function makeValidationRecord(name, valid, message) {
        return {
            name: name,
            valid: valid,
            message: message
        };
    }

    if (Object.values(checkValidity).every(isTrue)) {
        return {
            alertMessage: 'Successfully Submitted!',
			validationResults: [
				{ name: 'first-name', valid: true, message: null },
				{ name: 'last-name', valid: true, message: null },
				{ name: 'email', valid: true, message: null },
				{ name: 'date-of-birth', valid: true, message: null }
			]
        };
    } else {
		let alertMessage = 'You must correct:\n';
        if (!checkValidity.firstName) {
            alertMessage += 'First Name\n';
            validationResults.push(makeValidationRecord('first-name', false, 'Invalid first name'));
        } else {
            validationResults.push(makeValidationRecord('first-name', true, null));
        }
        if (!checkValidity.lastName) {
            alertMessage += 'Last Name\n';
            validationResults.push(makeValidationRecord('last-name', false, 'Invalid last name'));
        } else {
            validationResults.push(makeValidationRecord('last-name', true, null));
        }
        if (!checkValidity.email) {
            alertMessage += 'Email\n';
            validationResults.push(makeValidationRecord('email', false, 'Invalid email'));
        } else {
            validationResults.push(makeValidationRecord('email', true, null));
        }
        if (!checkValidity.birth) {
            alertMessage += 'Date of Birth\n';
            validationResults.push(makeValidationRecord('date-of-birth', false, 'Invalid date of birth'));
        } else {
            validationResults.push(makeValidationRecord('date-of-birth', true, null));
        }

        return {alertMessage, validationResults};
    }
}