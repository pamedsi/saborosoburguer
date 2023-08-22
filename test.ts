const getBurgerIdAndAmount = (longInString: string) => {
    return longInString.split(',').filter(character => Boolean(parseInt(character))).map(Number)
}
console.log(getBurgerIdAndAmount("45,27"))